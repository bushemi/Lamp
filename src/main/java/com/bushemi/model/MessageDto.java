package com.bushemi.model;

import java.time.LocalDateTime;
import java.util.Objects;


public class MessageDto {

    private long id;
    private String content;
    private LocalDateTime timeSent;
    private PersonDto PersonFrom;
    private PersonDto PersonTo;

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
        return PersonFrom;
    }

    public void setPersonFrom(PersonDto personFrom) {
        PersonFrom = personFrom;
    }

    public PersonDto getPersonTo() {
        return PersonTo;
    }

    public void setPersonTo(PersonDto personTo) {
        PersonTo = personTo;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        MessageDto that = (MessageDto) o;
        return id == that.id &&
                Objects.equals(content, that.content) &&
                Objects.equals(timeSent, that.timeSent) &&
                Objects.equals(PersonFrom, that.PersonFrom) &&
                Objects.equals(PersonTo, that.PersonTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, timeSent, PersonFrom, PersonTo);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MessageDto{");
        sb.append("id=").append(id);
        sb.append(", content='").append(content).append('\'');
        sb.append(", timeSent=").append(timeSent);
        sb.append(", PersonFrom=").append(PersonFrom);
        sb.append(", PersonTo=").append(PersonTo);
        sb.append('}');
        return sb.toString();
    }
}
