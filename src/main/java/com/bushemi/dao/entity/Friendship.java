package com.bushemi.dao.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "FRIENDSHIP")
public class Friendship {

    @EmbeddedId
    private FriendsPrimaryKey friends = new FriendsPrimaryKey();
    @Column(name = "FRIENDSHIP_DATE")
    private LocalDate friendFrom;


    public FriendsPrimaryKey getFriends() {
        return friends;
    }

    public void setFriends(FriendsPrimaryKey friends) {
        this.friends = friends;
    }

    @Transient
    public Person getPerson() {
        return friends.getPerson();
    }

    public void setPerson(Person person) {
        this.friends.setPerson(person);
    }
    @Transient
    public Person getFriend() {
        return friends.getFriend();
    }

    public void setFriend(Person friend) {
        this.friends.setFriend(friend);
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
        boolean result = Objects.equals(friends.getPerson(), that.friends.getPerson()) &&
                Objects.equals(friends.getFriend(), that.friends.getFriend());
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(friends.getPerson(), friends.getFriend());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Friendship{");
        sb.append("person=").append(friends.getPerson());
        sb.append(", friend=").append(friends.getFriend());
        sb.append(", friends From=").append(friendFrom);
        sb.append('}');
        return sb.toString();
    }
}
