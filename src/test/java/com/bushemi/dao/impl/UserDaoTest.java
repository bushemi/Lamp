package com.bushemi.dao.impl;

import com.bushemi.dao.PersonDao;
import com.bushemi.dao.UserDao;
import com.bushemi.model.PersonDto;
import com.bushemi.model.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by igor on 12.10.17.
 * useless comment
 */
@ContextConfiguration(locations = "classpath:app-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {

    @Autowired
    PersonDao personDao;
    @Autowired
    UserDao userDao;

    private EntityCreatorForDaoTests entityCreator;
    private static final String PREFIX = "_user";

    @Before
    public void createNewEntityCreator(){
        entityCreator = new EntityCreatorForDaoTests();
    }

    @Test
    public void create() throws Exception {
        PersonDto first = entityCreator.createNewPerson(personDao, "first" + PREFIX);
        UserDto user = entityCreator.createNewUser(userDao, "first" + PREFIX, first);
        Assert.notNull(user.getId(),"Creating new user was failed");
    }

    @Test
    public void update() throws Exception {
        PersonDto eighth = entityCreator.createNewPerson(personDao, "eighth" + PREFIX);
        UserDto user = entityCreator.createNewUser(userDao, "eighth" + PREFIX, eighth);
        user.setEmail("miami@us.gov");

        userDao.update(user);

    }

    @Test
    public void findAll() throws Exception {
        PersonDto second = entityCreator.createNewPerson(personDao, "second" + PREFIX);
        UserDto user2 = entityCreator.createNewUser(userDao, "second" + PREFIX, second);
        PersonDto third = entityCreator.createNewPerson(personDao, "third" + PREFIX);
        UserDto user3 = entityCreator.createNewUser(userDao, "third" + PREFIX, third);

        List<UserDto> users = userDao.findAll();
        Assert.notEmpty(users,"Can't find any users");
    }

    @Test
    public void findById() throws Exception {
        PersonDto forth = entityCreator.createNewPerson(personDao, "forth" + PREFIX);
        UserDto user = entityCreator.createNewUser(userDao, "forth" + PREFIX, forth);
        UserDto loadedUser = userDao.findById(user.getId());
        Assert.notNull(loadedUser.getId(),"Finding user by id was failed");
    }

    @Test(expected = NullPointerException.class)
    public void delete() throws Exception {
        PersonDto fifth = entityCreator.createNewPerson(personDao, "fifth" + PREFIX);
        UserDto user = entityCreator.createNewUser(userDao, "fifth" + PREFIX, fifth);
        userDao.delete(user);

        userDao.findById(user.getId());
    }

    @Test
    public void findByLogin() throws Exception {
        PersonDto sixth = entityCreator.createNewPerson(personDao, "sixth" + PREFIX);
        UserDto user = entityCreator.createNewUser(userDao, "sixth" + PREFIX, sixth);

        UserDto loadedUser = userDao.findByLogin(user.getLogin());
        Assert.notNull(loadedUser.getId(),"Finding user by login was failed");
    }

    @Test
    public void findByPerson() throws Exception {
        PersonDto seventh = entityCreator.createNewPerson(personDao, "seventh" + PREFIX);
        UserDto user = entityCreator.createNewUser(userDao, "seventh" + PREFIX, seventh);

        UserDto loadedUser = userDao.findByPerson(user.getPerson());
        Assert.notNull(loadedUser.getId(),"Finding user by person was failed");
    }


}