package com.bushemi.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;


public class MessageDto {

    private long id;
    private String content;
    private LocalDateTime timeSent;
    private PersonDto personFrom;
    private PersonDto personTo;

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

    public PersonDto getPersonFrom() {
        return personFrom;
    }

    public void setPersonFrom(PersonDto personFrom) {
        this.personFrom = personFrom;
    }

    public PersonDto getPersonTo() {
        return personTo;
    }

    public void setPersonTo(PersonDto personTo) {
        this.personTo = personTo;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        MessageDto that = (MessageDto) o;
        return id == that.id &&
                Objects.equals(content, that.content) &&
                Objects.equals(timeSent, that.timeSent) &&
                Objects.equals(personFrom, that.personFrom) &&
                Objects.equals(personTo, that.personTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, timeSent, personFrom, personTo);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MessageDto{");
        sb.append("id=").append(id);
        sb.append(", content='").append(content).append('\'');
        sb.append(", timeSent=").append(timeSent);
        sb.append(", personFrom=").append(personFrom);
        sb.append(", personTo=").append(personTo);
        sb.append('}');
        return sb.toString();
    }
}
