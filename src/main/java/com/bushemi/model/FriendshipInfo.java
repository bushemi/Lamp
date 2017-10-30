package com.bushemi.model;

import com.bushemi.model.entity.FriendshipDto;

import java.time.LocalDate;

/**
 * Created by igor on 24.10.17.
 * @Version 1.0
 * Utility class for jsp
 */
public class FriendshipInfo {
    private String personNickname;
    private String friendNickname;
    private LocalDate friendFrom;

    public FriendshipInfo() {
    }

    public FriendshipInfo(String personNickname, String friendNickname, LocalDate friendFrom) {
        this.personNickname = personNickname;
        this.friendNickname = friendNickname;
        this.friendFrom = friendFrom;
    }
    public FriendshipInfo(FriendshipDto friendship) {
        this(friendship.getPerson().getNickname(), friendship.getFriend().getNickname(), friendship.getFriendFrom());
    }

    public String getPersonNickname() {
        return personNickname;
    }

    public void setPersonNickname(String personNickname) {
        this.personNickname = personNickname;
    }

    public String getFriendNickname() {
        return friendNickname;
    }

    public void setFriendNickname(String friendNickname) {
        this.friendNickname = friendNickname;
    }

    public LocalDate getFriendFrom() {
        return friendFrom;
    }

    public void setFriendFrom(LocalDate friendFrom) {
        this.friendFrom = friendFrom;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FriendshipInfo{");
        sb.append("personNickname='").append(personNickname).append('\'');
        sb.append(", friendNickname='").append(friendNickname).append('\'');
        sb.append(", friendFrom=").append(friendFrom);
        sb.append('}');
        return sb.toString();
    }
}
