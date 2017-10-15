package com.bushemi.dao;


import com.bushemi.model.PersonDto;
import com.bushemi.model.PostDto;

import java.util.Collection;

/**
 * Created by igor on 01.09.17.
 * useless comment
 */
public interface PersonDao extends CrudOperationsInterface<PersonDto> {

    void createLike(PersonDto person, PostDto post);
    PersonDto findByNickname(String nickname);
    Collection<PostDto> findLikes(PersonDto person);

    Collection<PersonDto> findFriendsOf(PersonDto person);
}
