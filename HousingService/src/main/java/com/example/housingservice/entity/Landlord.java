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
@Table(name = "Landlord")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Landlord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "FirstName")
    private String FirstName;

    @Column(name = "LastName")
    private String LastName;

    @Column(name = "Email")
    private String Email;

    @Column(name = "CellPhone")
    private String CellPhone;

    @OneToMany(mappedBy = "landlord")
    private List<House> houseList;
}
