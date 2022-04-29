package com.example.housingservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Entity
@Table(name = "FacilityReport")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacilityReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @ManyToOne
    @JoinColumn(name = "FacilityID")
    private Facility facility;

    @Column(name = "EmployeeID")
    private String EmployeeID;

    @Column(name = "Title")
    private String Title;

    @Column(name = "Descriotion")
    private String Description;

    @Column(name = "Status")
    private String Status;

    @OneToMany(mappedBy = "facilityReport")
    private List<FacilityReportDetail> facilityReportDetailList;

}