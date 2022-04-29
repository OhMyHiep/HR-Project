package com.example.authentication.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "RegistrationToken")
public class RegistrationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "Token")
    private String Token;

    @Column(name = "Email")
    private String Email;

    @Column(name = "ExpirationDate")
    private Date ExpirationDate;

    @Column(name = "CreateBy")
    private String CreateBy;
}
