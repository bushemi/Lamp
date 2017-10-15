package com.bushemi.dao.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


@Entity
@Table(name = "PERSON")
public class Person {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    @Column(name = "NICKNAME", nullable = false, unique = true)
    private String nickname;

    @Column(name = "PHOTO", columnDefinition = "VARCHAR(255) not null default '/static/monkey.png'")
    private String photoURL = "/static/monkey.png";

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "PERSON_PLACE",
            inverseJoinColumns = @JoinColumn(name = "PLACE_ID"),
            joinColumns = @JoinColumn(name = "PERSON_ID")
    )
    private Collection<Place> places = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "PERSON_HOBBY",
            inverseJoinColumns = @JoinColumn(name = "HOBBY_ID"),
            joinColumns = @JoinColumn(name = "PERSON_ID")
    )
    private Collection<Hobby> hobbies =  new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "POST_LIKES",
            inverseJoinColumns = @JoinColumn(name = "POST_ID"),
            joinColumns = @JoinColumn(name = "PERSON_ID")
    )
    private Collection<Post> likes =  new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner")
    private Collection<Post> postAuthor =  new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "friends.person")
    private Collection<Friendship> friendships =  new ArrayList<>();

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

    public Collection<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Collection<Place> places) {
        this.places = places;
    }

    public Collection<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Collection<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public Collection<Post> getLikes() {
        return likes;
    }

    public void setLikes(Collection<Post> likes) {
        this.likes = likes;
    }

    public Collection<Post> getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(Collection<Post> postAuthor) {
        this.postAuthor = postAuthor;
    }

    public Collection<Friendship> getFriendships() {
        return friendships;
    }

    public void setFriendships(Collection<Friendship> friendships) {
        this.friendships = friendships;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(nickname, person.nickname);
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
