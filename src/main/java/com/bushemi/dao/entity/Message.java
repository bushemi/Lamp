package com.bushemi.dao.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


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

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "ID_PERSON_FROM")
    private Person PersonFrom;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "ID_PERSON_TO")
    private Person PersonTo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(LocalDateTime timeSent) {
        this.timeSent = timeSent;
    }

    public Person getPersonFrom() {
        return PersonFrom;
    }

    public void setPersonFrom(Person personFrom) {
        PersonFrom = personFrom;
    }

    public Person getPersonTo() {
        return PersonTo;
    }

    public void setPersonTo(Person personTo) {
        PersonTo = personTo;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Message message = (Message) o;
        return id == message.id &&
                Objects.equals(timeSent, message.timeSent);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeSent);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("id=").append(id);
        sb.append(", content='").append(content).append('\'');
        sb.append(", timeSent=").append(timeSent);
        sb.append(", from=").append(PersonFrom);
        sb.append(", to=").append(PersonTo);
        sb.append('}');
        return sb.toString();
    }
}
