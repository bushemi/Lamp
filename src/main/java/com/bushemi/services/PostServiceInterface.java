package com.bushemi.services;

import com.bushemi.entities.Person;
import com.bushemi.entities.Post;
import com.bushemi.entities.PostLikes;

import java.util.Collection;

/**
 * Created by igor on 16.08.17.
 */
public interface PostServiceInterface {
    Collection<Post> getLatestPostsForUser(Person person);
    Collection<PostLikes> getAllLikesForPost(Post post);

}
