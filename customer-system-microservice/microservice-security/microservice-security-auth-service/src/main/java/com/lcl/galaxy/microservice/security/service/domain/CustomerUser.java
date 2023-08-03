package com.lcl.galaxy.microservice.security.service.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cs_user")
@Data
public class CustomerUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private PasswordEncoderType passwordEncoderType;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<CustomerAuthority> authorities;
}
