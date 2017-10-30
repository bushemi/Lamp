package com.bushemi.dao;


import com.bushemi.model.entity.PersonDto;
import com.bushemi.model.entity.PostDto;

import java.util.Collection;

/**
 * Created by igor on 01.09.17.
 * useless comment
 */
public interface PostDao extends CrudOperationsInterface<PostDto> {
    Collection<PostDto> findPersonsPosts(PersonDto person);
    Collection<PersonDto> findPostLikers(PostDto post);

    Collection<PostDto> findPostsByOwnerId(long id);
}
