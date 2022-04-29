package com.GroupProject.applicationservice.service;

import com.GroupProject.applicationservice.dao.Impl.DigitalDocumentDaoImpl;
import com.GroupProject.applicationservice.domain.request.DigitalDocRequest;
import com.GroupProject.applicationservice.entity.DigitalDocument;
import com.GroupProject.applicationservice.entity.dto.DigitalDocumentDto;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DigitalDocumentService {

    private DigitalDocumentDaoImpl digitalDocumentDao;

    @Autowired
    public void setDigitalDocumentDao(DigitalDocumentDaoImpl digitalDocumentDao) {
        this.digitalDocumentDao = digitalDocumentDao;
    }

    private AmazonS3 amazonS3;


    private final String BUCKET="hr-employee-documents";
    private final String folder="digitalDocuments";

    @Autowired
    public void setAmazonS3(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }


    public List<String> showAllDocuments(){
        List<String> fileNames=new ArrayList<>();
        ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(BUCKET).withPrefix(folder);
        ListObjectsV2Result listing = amazonS3.listObjectsV2(req);
        for (S3ObjectSummary summary: listing.getObjectSummaries()) {
            String[] name= summary.getKey().split("/");
            fileNames.add(name[name.length-1]);
        }
        return fileNames.size()!=0? fileNames:null;
    }



    @Transactional
    public List<DigitalDocumentDto> uploadDigitlDocuments(DigitalDocRequest request, MultipartFile file){
        List<DigitalDocument> docList=new ArrayList<>();

        //send files to aws s3
            String fileName = file.getOriginalFilename();
            File convertedFile = new File(fileName);
            try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
                fos.write(file.getBytes());
            } catch (Exception e) {

            }
            String path= folder+"/"+fileName;
//            System.out.println(fileName);
            amazonS3.putObject(new PutObjectRequest(BUCKET, path, convertedFile));

            convertedFile.delete();

            //upload doc data to mysql
            DigitalDocument document= DigitalDocument.builder()
                    .path(path)
                    .isRequired(request.getIsRequired())
                    .type(request.getType())
                    .description(request.getDescription())
                    .title(fileName)
                    .build();
            digitalDocumentDao.add(document);
            docList.add(document);

        return docList.stream().map(
                doc-> DigitalDocumentDto.builder()
                        .path(doc.getPath())
                        .title(doc.getTitle())
                        .build())
                .collect(Collectors.toList());
    }

    public byte[] downloadFile(Optional<String> fileName) {

        try {
            S3Object s3Object = amazonS3.getObject(BUCKET, folder+"/"+fileName.get());
            S3ObjectInputStream inputStream = s3Object.getObjectContent();
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }catch (AmazonS3Exception e){
            System.out.println("The specified key does not exist");
        }
        return null;
    }

}
