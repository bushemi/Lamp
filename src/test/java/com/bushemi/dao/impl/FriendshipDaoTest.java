package com.bushemi.dao.impl;

import com.bushemi.dao.FriendshipDao;
import com.bushemi.dao.PersonDao;
import com.bushemi.model.FriendshipDto;
import com.bushemi.model.PersonDto;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Created by igor on 13.10.17.
 * useless comment
 */
@ContextConfiguration(locations = "classpath:app-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class FriendshipDaoTest {
    @Autowired
    PersonDao personDao;
    @Autowired
    FriendshipDao friendshipDao;

    private EntityCreatorForDaoTests entityCreator;

    @Before
    public void createNewEntityCreator(){
        entityCreator = new EntityCreatorForDaoTests();
    }

    @Test
    public void create() throws Exception {
        PersonDto first = entityCreator.createNewPerson(personDao, "firstFs");
        PersonDto second = entityCreator.createNewPerson(personDao, "secondFs");
        FriendshipDto friendship =  entityCreator.createNewFriendship(friendshipDao, first, second);
    }

    @Test(expected=DataIntegrityViolationException.class)
    public void createFail() throws Exception {
        PersonDto first = entityCreator.createNewPerson(personDao, "fifthFs");
        PersonDto second = entityCreator.createNewPerson(personDao, "sixthFs");
        FriendshipDto friendship1 =  entityCreator.createNewFriendship(friendshipDao, first, second);
        try {
            FriendshipDto friendship2 = entityCreator.createNewFriendship(friendshipDao, first, second);
        }catch(ConstraintViolationException e){
            throw new DataIntegrityViolationException("",e);
        }

        throw new AssertionError("error with insert two friendships with same primary key(it must to be unique)");
    }

    @Test
    public void delete() throws Exception {
        PersonDto first = entityCreator.createNewPerson(personDao, "thirdFs");
        PersonDto second = entityCreator.createNewPerson(personDao, "forthFs");
        FriendshipDto friendship =  entityCreator.createNewFriendship(friendshipDao, first, second);

        friendshipDao.delete(friendship);

        Collection<FriendshipDto> friendships = friendshipDao.findFriendsOf(first);
        if (friendships.size() > 0) { throw new AssertionError("error with delete friendship");}
    }

    @Test
    public void update() throws Exception {
        PersonDto first = entityCreator.createNewPerson(personDao, "thirteenthFs");
        PersonDto second = entityCreator.createNewPerson(personDao, "fourteenthFs");
        FriendshipDto friendship =  entityCreator.createNewFriendship(friendshipDao, first, second);
        friendship.setFriendFrom(LocalDate.now().plusDays(2));
        friendshipDao.update(friendship);
    }

    @Test
    public void findAllForOneFriendship() throws Exception {
        PersonDto first = entityCreator.createNewPerson(personDao, "seventhFs");
        PersonDto second = entityCreator.createNewPerson(personDao, "eighthFs");
        FriendshipDto friendship =  entityCreator.createNewFriendship(friendshipDao, first, second);

        Collection<FriendshipDto> friendships = friendshipDao.findAll();
        Assert.notEmpty(friendships,"Can't find any friendship");
    }

    @Test
    public void findAllForTwoFriendships() throws Exception {
        PersonDto first = entityCreator.createNewPerson(personDao, "ninthFs");
        PersonDto second = entityCreator.createNewPerson(personDao, "tenthFs");
        FriendshipDto friendship =  entityCreator.createNewFriendship(friendshipDao, first, second);
        FriendshipDto friendship2 =  entityCreator.createNewFriendship(friendshipDao, second, first);

        Collection<FriendshipDto> friendships = friendshipDao.findAll();
        Assert.notEmpty(friendships,"Can't find any friendships");
    }

    @Test
    public void findFriendsOf() throws Exception {
        PersonDto first = entityCreator.createNewPerson(personDao, "eleventhFs");
        PersonDto second = entityCreator.createNewPerson(personDao, "twelfthFs");
        FriendshipDto friendship =  entityCreator.createNewFriendship(friendshipDao, first, second);

        Collection<FriendshipDto> friendships = friendshipDao.findFriendsOf(first);
        Assert.notEmpty(friendships,"Can't find any friends of specific person");
    }

}