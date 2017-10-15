package com.bushemi.dao;


import com.bushemi.model.FriendshipDto;
import com.bushemi.model.PersonDto;

import java.util.Collection;

/**
 * Created by igor on 01.09.17.
 * useless comment
 */
public interface FriendshipDao  {
    void create(FriendshipDto entity);
    void delete(FriendshipDto entity);
    void update(FriendshipDto entity);
    Collection<FriendshipDto> findAll();
    Collection<FriendshipDto> findFriendsOf(PersonDto person);
}
