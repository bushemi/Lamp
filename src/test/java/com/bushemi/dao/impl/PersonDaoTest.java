package com.bushemi.dao.impl;

import com.bushemi.dao.FriendshipDao;
import com.bushemi.dao.PersonDao;
import com.bushemi.dao.PostDao;
import com.bushemi.model.entity.FriendshipDto;
import com.bushemi.model.entity.PersonDto;
import com.bushemi.model.entity.PostDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.Collection;


/**
 * Created by igor on 11.10.17.
 * useless comment
 */
@ContextConfiguration(locations = "classpath:app-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonDaoTest {
    @Autowired
    private PersonDao personDao;
    @Autowired
    private PostDao postDao;
    @Autowired
    FriendshipDao friendshipDao;

    private EntityCreatorForDaoTests entityCreator;

    @Before
    public void createNewEntityCreator(){
        entityCreator = new EntityCreatorForDaoTests();
    }



    @Test
    public void saveNewEntity() throws Exception {

        Long id = entityCreator.createNewPerson(personDao, "first1").getId();
        Assert.notNull(id,"Creating new person was failed");
    }

    @Test(expected = org.hibernate.exception.ConstraintViolationException.class)
    public void saveTwoEntitiesWithSameNickname() throws Exception {

        Long id1 = entityCreator.createNewPerson(personDao, "first0").getId();
        Long id2 = entityCreator.createNewPerson(personDao, "first0").getId();
    }

    @Test
    public void createAndLoadById() throws Exception {
        Long id = entityCreator.createNewPerson(personDao, "second2").getId();

        PersonDto loadedPerson = personDao.findById(id);
        Assert.notNull(loadedPerson,"Finding new person by id was failed");
    }

    @Test(expected = NullPointerException.class)
    public void delete() throws Exception {
        PersonDto newPerson = entityCreator.createNewPerson(personDao, "third3");
        personDao.delete(newPerson);

        personDao.findById(newPerson.getId());

    }

    @Test
    public void updateNickname() throws Exception {
        PersonDto newPerson = entityCreator.createNewPerson(personDao, "forth4");
        newPerson.setNickname("tenth10");
        PersonDto loadedPerson = personDao.update(newPerson);
        Assert.notNull(loadedPerson.getId(),"person update was failed");
    }

    @Test
    public void updateNicknameWithNullId() throws Exception {
        PersonDto newPerson = entityCreator.createNewPerson(personDao, "forth0");
        newPerson.setId(null);
        newPerson.setNickname("tenth0");
        PersonDto loadedPerson = personDao.update(newPerson);
        Assert.notNull(loadedPerson.getId(),"updating nickname with null id's person was failed");
    }

    @Test
    public void findAll() throws Exception {
        PersonDto first = entityCreator.createNewPerson(personDao, "fifth5");
        PersonDto second = entityCreator.createNewPerson(personDao, "sixth6");
        Collection<PersonDto> persons = personDao.findAll();
        Assert.notEmpty(persons,"Can't find any persons");
    }

    @Test
    public void createLike() throws Exception {
        PersonDto first = entityCreator.createNewPerson(personDao, "seventh7");

        PostDto post = entityCreator.createNewPost(postDao,first);
//        post.setTitle("Title");
//        post.setContent("My post begins from uppercase letter.");
//        post.setOwner(first);
//        post.setPlaceTime(LocalDateTime.now());
        personDao.createLike(first, post);
        Collection<PostDto> likes = personDao.findLikes(first);
//        System.out.println(likes.size());

        Assert.notEmpty(likes,"Can't find any likes for this person with id:" + first.getId());
    }

    @Test
    public void findByNickname() throws Exception {
        PersonDto first = entityCreator.createNewPerson(personDao, "eighth8");
        PersonDto second = entityCreator.createNewPerson(personDao, "ninth9");
        PersonDto loadedFirst = personDao.findByNickname("eighth8");
    }

    @Test
    public void findFriendsOf() throws Exception  {
        PersonDto first = entityCreator.createNewPerson(personDao, "fifthF");
        PersonDto second = entityCreator.createNewPerson(personDao, "sixthF");
        FriendshipDto friendship1 =  entityCreator.createNewFriendship(friendshipDao, first, second);
    }



}