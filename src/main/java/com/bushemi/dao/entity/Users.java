package com.bushemi.dao.entity;

import javax.persistence.*;

/**
 * Created by igor on 01.10.17.
 * useless comment
 */

@Entity
@Table(name = "USER")
public class Users {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "LOGIN", nullable = false, unique = true)
    private String login;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @OneToOne(targetEntity = Person.class)
    @JoinColumn(name = "PERSON_ID")
    private Person person;
}
