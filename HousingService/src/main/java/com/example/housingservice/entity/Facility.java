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
@Table(name = "Facility")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @ManyToOne
    @JoinColumn(name = "HouseID")
    private House house;

    @Column(name = "Type")
    private String Type;

    @Column(name = "Description")
    private String Description;

    @Column(name = "Quantity")
    private String Quantity;

    @OneToMany(mappedBy = "facility")
    private List<FacilityReport> facilityReportList;
}
