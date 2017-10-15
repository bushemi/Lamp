package com.bushemi.dao.impl;

import com.bushemi.dao.PersonDao;
import com.bushemi.dao.PostDao;
import com.bushemi.model.PersonDto;
import com.bushemi.model.PostDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * Created by igor on 12.10.17.
 * useless comment
 */
@ContextConfiguration(locations = "classpath:app-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PostDaoTest {
    @Autowired
    PersonDao personDao;
    @Autowired
    PostDao postDao;

    private EntityCreatorForDaoTests entityCreator;

    @Before
    public void createNewEntityCreator(){
        entityCreator = new EntityCreatorForDaoTests();
    }

    @Test
    public void saveNewEntity() throws Exception {
        PersonDto first = entityCreator.createNewPerson(personDao, "first");
        PostDto post = entityCreator.createNewPost(postDao, first);
        Assert.notNull(post.getId(),"Creating new post was failed");
    }

    @Test
    public void createAndLoadById() throws Exception {
        PersonDto second = entityCreator.createNewPerson(personDao, "second");
        PostDto post = entityCreator.createNewPost(postDao, second);
        PostDto loadedPost = postDao.findById(post.getId());
        Assert.notNull(loadedPost,"Finding post by id was failed");
    }

    @Test(expected = NullPointerException.class)
    public void delete() throws Exception {
        PersonDto third = entityCreator.createNewPerson(personDao, "third");
        PostDto post = entityCreator.createNewPost(postDao, third);
        postDao.delete(post);

        postDao.findById(post.getId());
    }

    @Test
    public void update() throws Exception {
        PersonDto forth = entityCreator.createNewPerson(personDao, "forth");
        PostDto post = entityCreator.createNewPost(postDao, forth);
        post.setTitle("Rumours");

        PostDto updatedPost = postDao.update(post);
    }

    @Test
    public void findAll() throws Exception {
        PersonDto fifth = entityCreator.createNewPerson(personDao, "fifth");
        PostDto post1 = entityCreator.createNewPost(postDao, fifth);
        PostDto post2 = entityCreator.createNewPost(postDao, fifth);
        post2.setTitle("Rumours");

        Collection<PostDto> posts = postDao.findAll();

        Assert.notEmpty(posts,"Can't find any posts");
    }

    @Test
    public void findAllPostsByPerson() throws Exception {
        PersonDto sixth = entityCreator.createNewPerson(personDao, "sixth");
        PostDto post1 = entityCreator.createNewPost(postDao, sixth);
        PostDto post2 = entityCreator.createNewPost(postDao, sixth);

        Collection<PostDto> allPostsByPerson = postDao.findPersonsPosts(sixth);
        Assert.notEmpty(allPostsByPerson,"Can't find any posts by this person");
    }

    @Test
    public void findAllLikersForPost() throws Exception {
        PersonDto seventh = entityCreator.createNewPerson(personDao, "seventh");
        PersonDto eighth = entityCreator.createNewPerson(personDao, "eighth");
        PostDto post = entityCreator.createNewPost(postDao, seventh);

        personDao.createLike(seventh, post);
        personDao.createLike(eighth, post);

        Collection<PersonDto> allLikersForPost = postDao.findPostLikers(post);
        Assert.notEmpty(allLikersForPost,"Can't find any likers for his post");
    }

}