package com.example.housingservice.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HouseResponse {

    private String address;

    private String landlordName;

    private String phone;

    private String email;

    private Integer maxOccupant;


}
