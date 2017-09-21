package com.bushemi.dao.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "Friendship")
public class Friendship {

    @OneToOne(targetEntity = Person.class)
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @OneToOne(targetEntity = Person.class)
    @JoinColumn(name = "FRIEND_ID")
    private Person friend;

    @Column(name = "FRIENDSHIP_DATE")
    private LocalDate friendFrom;


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getFriend() {
        return friend;
    }

    public void setFriend(Person friend) {
        this.friend = friend;
    }

    public LocalDate getFriendFrom() {
        return friendFrom;
    }

    public void setFriendFrom(LocalDate friendFrom) {
        this.friendFrom = friendFrom;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Friendship that = (Friendship) o;
        return person == that.person &&
                friend == that.friend;
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, friend, friendFrom);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Friendship{");
        sb.append("person=").append(person);
        sb.append(", friend=").append(friend);
        sb.append(", friends From=").append(friendFrom);
        sb.append('}');
        return sb.toString();
    }
}
