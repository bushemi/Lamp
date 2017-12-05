package com.bushemi.service.impl;

import com.bushemi.dao.PersonDao;
import com.bushemi.dao.PostDao;
import com.bushemi.model.entity.PersonDto;
import com.bushemi.model.entity.PostDto;
import com.bushemi.model.entity.PostLikesDto;
import com.bushemi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by igor on 08.10.17.
 * useless comment
 */
@Service
@Transactional

public class PostServiceImpl implements PostService {


    final private PersonDao personDao;
    final private PostDao postDao;

    @Autowired
    public PostServiceImpl(PersonDao personDao, PostDao postDao) {
        this.personDao = personDao;
        this.postDao = postDao;
    }


    @Override
    public Collection<PostDto> getLatestPostsForUser(PersonDto person) {
        return null;
    }

    @Override
    public Collection<PostLikesDto> getAllLikesForPost(PostDto post) {
        postDao.update(post);
        postDao.findById(post.getId());

        return null;
    }

    @Override
    public Collection<PersonDto> getAllLikersForPost(PostDto post) {
        return postDao.findPostLikers(post);
    }

    @Override
    public void likePost(PersonDto person, PostDto post) {
        PostLikesDto likesDto = new PostLikesDto();
        likesDto.setLiker(person);
        likesDto.setPost(post);

        personDao.createLike(person, post);

    }

    @Override
    public Collection<PostDto> getAllPosts() {
        return postDao.findAll();
    }

    @Override
    public int countLikesForPost(PostDto postDto) {

        int size = getAllLikersForPost(postDto).size();
        return size;
    }

    @Override
    public PostDto createNewPost(PersonDto owner, String title, String content) {
        PostDto post = new PostDto();
        post.setOwner(owner);
        post.setContent(content);
        post.setPlaceTime(LocalDateTime.now());
        post.setTitle(title);
        Long postId = postDao.create(post);
        post.setId(postId);
        return post;
    }

    @Override
    public void createLike(PersonDto person, PostDto postDto) {
        Collection<PostDto> likes = personDao.findLikes(person);

       if(!likes.contains(postDto))
       {
           System.out.println("like "+likes.size());personDao.createLike(person, postDto);}
       else{System.out.println("dislike "+likes.size());personDao.disLike(person, postDto);}
    }
}
