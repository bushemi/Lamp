package com.bushemi.model;

import com.bushemi.model.entity.PersonDto;

import java.time.LocalDate;

/**
 * Created by igor on 19.10.17.
 * @Version 1.01
 * 1.01.was added id field to handle persons list
 * Utility class for jsp
 */
public class PersonInfo {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String nickname;
    private String photoURL;

    public PersonInfo() {
    }
    public PersonInfo(PersonDto person) {
        this(person.getId(), person.getFirstName(),person.getLastName(),person.getBirthday(),person.getNickname(),person.getPhotoURL());
    }

    public PersonInfo(Long id, String firstName, String lastName, LocalDate birthday, String nickname, String photoURL) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.nickname = nickname;
        this.photoURL = photoURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonInfo{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthday=").append(birthday);
        sb.append(", nickname='").append(nickname).append('\'');
        sb.append(", photoURL='").append(photoURL).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
