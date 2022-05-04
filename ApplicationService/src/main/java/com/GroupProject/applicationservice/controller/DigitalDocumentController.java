package com.GroupProject.applicationservice.controller;

import com.GroupProject.applicationservice.domain.request.DigitalDocRequest;
import com.GroupProject.applicationservice.domain.response.ResponseStatus;
import com.GroupProject.applicationservice.entity.dto.DigitalDocumentDto;
import com.GroupProject.applicationservice.service.DigitalDocumentService;
import com.GroupProject.applicationservice.domain.response.AllDigitalDocResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
public class DigitalDocumentController {

    private DigitalDocumentService digitalDocumentService;

    @Autowired
    public void setDigitalDocumentService(DigitalDocumentService digitalDocumentService) {
        this.digitalDocumentService = digitalDocumentService;
    }

    @GetMapping
    public List<String> showAllDocuments() {
        System.out.println("in show all documents");
        return digitalDocumentService.showAllDocuments();
    }


    @GetMapping("download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Optional<String> fileName){
        if(fileName.isPresent()){
            byte[] data=digitalDocumentService.downloadFile(fileName);
            ByteArrayResource resource=new ByteArrayResource(data);
            return ResponseEntity
                    .ok()
                    .contentLength(data.length)
                    .header("content-type","application/octet-stream")
                    .header("Content-disposition", "attachment; filename=\"" + fileName.get() + "\"")
                    .body(resource);
        }
        return null;
    }

    @PostMapping("documents/upload")
    public AllDigitalDocResponse uploadDigitlDocuments(@RequestParam("formDetails") String form,@RequestParam("file") MultipartFile file)throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        DigitalDocRequest digitalDocRequest=mapper.readValue(form,DigitalDocRequest.class);
        List<DigitalDocumentDto> docDtoList= digitalDocumentService.uploadDigitlDocuments(digitalDocRequest,file);
        return AllDigitalDocResponse.builder()
                .documentDtoList(docDtoList)
                .status(ResponseStatus.builder()
                        .success(true)
                        .message("documents added")
                        .build())
                .build();
    }



}
