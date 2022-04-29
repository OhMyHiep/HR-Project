package com.example.authentication.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "User")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "Email")
    private String email;

    // Database table
    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;
    // @OneToMany


    @Column(name = "CreateDate")
    private Date CreateDate;

    @Column(name = "LastModificationDate")
    private Date LastModificationDate;

    @Column(name = "ActiveFlag")
    private Boolean ActiveFlag;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserRole> userRole;
}
