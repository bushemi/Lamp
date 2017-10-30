package com.bushemi.service;

import com.bushemi.model.entity.PersonDto;
import com.bushemi.model.entity.PostDto;
import com.bushemi.model.entity.PostLikesDto;

import java.util.Collection;


public interface PostService {
    Collection<PostDto> getLatestPostsForUser(PersonDto person);
    Collection<PostLikesDto> getAllLikesForPost(PostDto post);
    Collection<PersonDto> getAllLikersForPost(PostDto post);
    void likePost(PersonDto person, PostDto post);
    Collection<PostDto> getAllPosts();
    int countLikesForPost(PostDto postDto);

    PostDto createNewPost(PersonDto owner, String title, String content);

    void createLike(PersonDto person,PostDto postDto);
}
