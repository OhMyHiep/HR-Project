package com.example.compositeservice.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.example.compositeservice.domain.response.CheckStatusResponse;
import com.example.compositeservice.domain.response.CompositeResponse;
import com.example.compositeservice.domain.response.UpdateResponse;
import com.example.compositeservice.entity.ApplicationService.ApplicationWorkFlow;
import com.example.compositeservice.domain.request.ApplicationFormRequest;
import com.example.compositeservice.domain.response.AppWorkFlowResponse;
import com.example.compositeservice.entity.EmployeeService.Employee;
import com.example.compositeservice.service.CompositeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
public class CompositeController {

    private CompositeService compositeService;
    private TransferManager transferManager;
    private AmazonS3 amazonS3;

    @Autowired
    public void setAmazonS3(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Autowired
    public void setTransferManager(TransferManager transferManager) {
        this.transferManager = transferManager;
    }

    @Autowired
    public void setCompositeService(CompositeService compositeService) {
        this.compositeService = compositeService;
    }

    @PostMapping("/upload")
    public CompositeResponse addEmployeeApplication(@RequestParam("applicationForm") String form, @RequestParam("file") MultipartFile[] files)throws IOException{
        //work flow:
        // 1. get the time                                                          X
        // 2. rename the file based on the username + time                          X
        // 3. upload the files to AWS S3                                            X
        // 4. Submit the Employee ------ Return the Employee from database with ID  X
        // 5. Use Employee returned from 4 to get ID and Save to 5                  X

        //NOTE: IN postman: date format as follow: YYYY-MM-DDTHH:MM:SS

        //TO-DO:
        //Adding path to personal document


        SimpleDateFormat month = new SimpleDateFormat("MMM");
        SimpleDateFormat day = new SimpleDateFormat("dd");
        SimpleDateFormat year = new SimpleDateFormat("yyy");
        SimpleDateFormat hour = new SimpleDateFormat("HH");
        SimpleDateFormat min = new SimpleDateFormat("mm");
        SimpleDateFormat sec = new SimpleDateFormat("ss");

        Date now = new Date(System.currentTimeMillis());

        //Mapping the String to applicationForm class
        ObjectMapper mapper = new ObjectMapper();
        ApplicationFormRequest application = mapper.readValue(form, ApplicationFormRequest.class);
        application.setApplicationSubmittedTime(now);

//        String timeStr = String.format("%s/%s/%s",year.format(now),month.format(now),day.format(now));
        System.out.println(application);
        //uploading files to Aws S3
        for(MultipartFile multipartFile: files){
            String fileName= multipartFile.getOriginalFilename();
            File convertedFile = new File(fileName);
            try(FileOutputStream fos = new FileOutputStream(fileName)){
                fos.write(multipartFile.getBytes());
            }catch(Exception e){
                e.printStackTrace();
            }
            amazonS3.putObject(new PutObjectRequest("hr-employee-documents",fileName,convertedFile));
            System.out.println("Uploaded successfully "+fileName);
        }

        //Sending application form to employeeService for adding to Mongo
        Employee savedEmployee = compositeService.addEmployeeApplication(application);
//
        //uploading application to mysql
//        AppWorkFlowResponse savedApplication = compositeService.add(Optional.ofNullable(
//                ApplicationWorkFlow.builder()
//                        .employeeID(savedEmployee.getID())
//                        .createDate(now)
//                        .lastModificationDate(now)
//                        .comment("")
//                        .status("pending")
//                        .build()
//                )
//        );

        //uploading application to mysql
        ApplicationWorkFlow savedApplication = compositeService.submit(
                ApplicationWorkFlow.builder()
                        .employeeID(savedEmployee.getID())
                        .createDate(now)
                        .lastModificationDate(now)
                        .comment("")
                        .status("pending")
                        .build()
        );

        return CompositeResponse.builder()
                .employee(savedEmployee)
                .application(savedApplication)
                .build();
    }

    @GetMapping("{id}")
    public CheckStatusResponse checkEmployeeStatus(@PathVariable String id){
        if (!compositeService.checkApplicationStatus(id)){
            return CheckStatusResponse.builder().pass(false).message("Sorry, your application is rejected or in process").build();
        }else{
            Optional<Employee> employee = compositeService.getEmployeeById(id);
            return CheckStatusResponse.builder().pass(true). employee(employee.get()).build();
        }
    }

    @PostMapping("{id}")
    public UpdateResponse updateEmployee(@PathVariable String id,@RequestBody Employee e){

        if(compositeService.checkApplicationStatus(id)){
            e.setID(id);
            Employee updatedEmployee =  compositeService.updateEmployee(e);
            return UpdateResponse.builder().employee(updatedEmployee).build();
        }
        else{
            return UpdateResponse.builder().message("Sorry, you are not allowed to update the information at this time").build();
        }
    }
}
//  nowDungLe_dictiona.txt
//https://hr-employee-documents.s3.amazonaws.com/dictionary.txt
