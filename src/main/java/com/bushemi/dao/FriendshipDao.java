package com.bushemi.dao;


import com.bushemi.model.entity.FriendshipDto;
import com.bushemi.model.entity.PersonDto;

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
    Collection<FriendshipDto> findFriendshipsOf(PersonDto person);

    Collection<FriendshipDto> findFriendshipsByPersonId(long id);
    boolean isFriends(PersonDto person, PersonDto friend);
}
