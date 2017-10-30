package com.bushemi.model.entity;

import java.time.LocalDate;
import java.util.Objects;


public class FriendshipDto {

    private PersonDto person;
    private PersonDto friend;
    private LocalDate friendFrom;

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public PersonDto getFriend() {
        return friend;
    }

    public void setFriend(PersonDto friend) {
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
        FriendshipDto that = (FriendshipDto) o;
        boolean result = Objects.equals(person, that.person) &&
                Objects.equals(friend, that.friend) &&
                friendFrom.isEqual(that.friendFrom);
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, friend, friendFrom);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FriendshipDto{");
        sb.append("person=").append(person);
        sb.append(", friend=").append(friend);
        sb.append(", friendFrom=").append(friendFrom);
        sb.append('}');
        return sb.toString();
    }
}
