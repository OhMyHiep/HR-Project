package com.example.authentication.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "Role")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "RoleName")
    private String RoleName;

    @Column(name = "RoleDescription")
    private String RoleDescription;

    @Column(name = "CreateDate")
    private Date CreateDate;

    @Column(name = "LastModificationDate")
    private Date LastModificationDate;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<UserRole> userRoleList;
}
