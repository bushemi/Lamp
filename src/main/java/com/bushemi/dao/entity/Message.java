package com.bushemi.dao.entity;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "MESSAGE")
public class Message {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "TIME_SENT")
    private LocalDateTime timeSent;

    @Column(name = "ID_PERSON_FROM")
    @OneToOne(targetEntity = Person.class)
    @JoinColumn(name = "id")
    private long idPersonFrom;

    @Column(name = "ID_PERSON_TO")
    @OneToOne(targetEntity = Person.class)
    @JoinColumn(name = "id")
    private long idPersonTo;


}
