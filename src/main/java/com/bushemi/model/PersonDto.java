package com.bushemi.model;

import java.time.LocalDate;
import java.util.Objects;


public class PersonDto {

    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String nickname;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        PersonDto personDto = (PersonDto) o;
        return id == personDto.id &&
                Objects.equals(firstName, personDto.firstName) &&
                Objects.equals(lastName, personDto.lastName) &&
                Objects.equals(birthday, personDto.birthday) &&
                Objects.equals(nickname, personDto.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthday, nickname);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonDto{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthday=").append(birthday);
        sb.append(", nickname='").append(nickname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
