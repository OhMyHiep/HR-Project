package com.example.housingservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Entity
@Table(name = "House")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class House implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @ManyToOne
    @JoinColumn(name = "LandlordID")
    private Landlord landlord;

    @Column(name = "Address")
    private String Address;

    @Column(name = "MaxOccupant")
    private Integer MaxOccupant;

    @OneToMany(mappedBy = "house")
    private List<Facility> facilityList;
}
