package com.bushemi.service.impl;

import com.bushemi.dao.PersonDao;
import com.bushemi.dao.impl.EntityCreatorForDaoTests;
import com.bushemi.model.entity.PersonDto;
import com.bushemi.model.entity.PostDto;
import com.bushemi.service.PostService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by igor on 27.10.17.
 *
 * @Version 1.0
 */
@ContextConfiguration(locations = "classpath:app-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PostServiceImplTest {
    @Autowired
    PostService postService;
    @Autowired
    PersonDao personDao;

    EntityCreatorForDaoTests entityCreator = new EntityCreatorForDaoTests();
    @Test
    public void countLikesForPost() throws Exception {
        PersonDto third = entityCreator.createNewPerson(personDao, "thirdPostServiceTest");
        PostDto newPost = postService.createNewPost(third, "one", "two");
        personDao.createLike(third, newPost);
        int countLikesForPost = postService.countLikesForPost(newPost);
        System.out.println(countLikesForPost);
        Assert.assertTrue("Count likes for post Test was failed.",(countLikesForPost > 0));
    }

}