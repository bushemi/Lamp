package com.bushemi.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


public class PersonDto {

    private Long id;
    private String firstName = "John";
    private String lastName = "Doe";
    private LocalDate birthday;
    private String nickname;
    private String photoURL;
    private Collection<PlaceDto> places = new ArrayList<>();
    private Collection<HobbyDto> hobbies = new ArrayList<>();
    private Collection<PostDto> likes = new ArrayList<>();
    private Collection<PostDto> postAuthor = new ArrayList<>();
    private Collection<FriendshipDto> friendships = new ArrayList<>();

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

    public Collection<PlaceDto> getPlaces() {
        return places;
    }

    public void setPlaces(Collection<PlaceDto> places) {
        this.places = places;
    }

    public Collection<HobbyDto> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Collection<HobbyDto> hobbies) {
        this.hobbies = hobbies;
    }

    public Collection<PostDto> getLikes() {
        return likes;
    }

    public void setLikes(Collection<PostDto> likes) {
        this.likes = likes;
    }

    public Collection<PostDto> getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(Collection<PostDto> postAuthor) {
        this.postAuthor = postAuthor;
    }

    public Collection<FriendshipDto> getFriendships() {
        return friendships;
    }

    public void setFriendships(Collection<FriendshipDto> friendships) {
        this.friendships = friendships;
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
                Objects.equals(photoURL, personDto.photoURL) &&
                Objects.equals(nickname, personDto.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
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
