package com.example.compositeservice.entity.ApplicationService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationWorkFlow implements Serializable {
    private BigInteger ID;
    private String employeeID;
    private Date createDate;
    private Date lastModificationDate;
    private String status;
    private String comment;
}
