package com.bushemi.dao.entity;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by igor on 04.10.17.
 * useless comment
 */
@Embeddable
public class FriendsPrimaryKey  implements Serializable{
    @ManyToOne(optional = true, cascade =  CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID", insertable=false, updatable=false)
    private Person person;

    @ManyToOne(optional = true, cascade =  CascadeType.ALL)
    @JoinColumn(name = "FRIEND_ID", insertable=false, updatable=false)
    private Person friend;


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

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        FriendsPrimaryKey that = (FriendsPrimaryKey) o;
        return Objects.equals(person, that.person) &&
                Objects.equals(friend, that.friend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, friend);
    }

}
