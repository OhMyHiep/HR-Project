package com.example.housingservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Entity
@Table(name = "FacilityReport")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacilityReportDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @ManyToOne
    @JoinColumn(name = "FacilityReportID")
    private FacilityReport facilityReport;

    @Column(name = "EmployeeID")
    private String EmployeeID;

    @Column(name = "Comment")
    private String Comment;

    @Column(name = "CreateDate")
    private Date CreateDate;

    @Column(name = "LastModificationDate")
    private Date LastModificationDate;


}