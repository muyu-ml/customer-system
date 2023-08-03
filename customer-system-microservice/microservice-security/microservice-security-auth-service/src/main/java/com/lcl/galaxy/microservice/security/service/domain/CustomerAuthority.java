package com.lcl.galaxy.microservice.security.service.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cs_authority")
@Data
public class CustomerAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @JoinColumn(name = "user")
    @ManyToOne
    private CustomerUser user;
}
