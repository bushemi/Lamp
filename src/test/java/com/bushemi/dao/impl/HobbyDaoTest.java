package com.bushemi.dao.impl;

import com.bushemi.dao.HobbyDao;
import com.bushemi.dao.PersonDao;
import com.bushemi.model.entity.HobbyDto;
import com.bushemi.model.entity.PersonDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * Created by igor on 13.10.17.
 * useless comment
 */
@ContextConfiguration(locations = "classpath:app-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class HobbyDaoTest {
    @Autowired
    private PersonDao personDao;
    @Autowired
    private HobbyDao hobbyDao;

    private EntityCreatorForDaoTests entityCreator;

    @Before
    public void createNewEntityCreator(){
        entityCreator = new EntityCreatorForDaoTests();
    }


    @Test
    public void create() throws Exception {
        HobbyDto hobby = entityCreator.createNewHobby(hobbyDao);

        Assert.notNull(hobby.getId(),"Creating new hobby was failed");
    }

    @Test
    public void findById() throws Exception {
        HobbyDto hobby = entityCreator.createNewHobby(hobbyDao);

        HobbyDto loadedHobby = hobbyDao.findById(hobby.getId());
        Assert.notNull(loadedHobby.getId(),"Load hobby by id was failed");
    }

    @Test(expected = NullPointerException.class)
    public void delete() throws Exception {
        HobbyDto hobby = entityCreator.createNewHobby(hobbyDao);

        hobbyDao.delete(hobby);

        HobbyDto loadedHobby = hobbyDao.findById(hobby.getId());
    }

    @Test
    public void update() throws Exception {
        HobbyDto hobby = entityCreator.createNewHobby(hobbyDao);
        hobby.setDescription("swimming in the Dnipro");

        HobbyDto updatedHobby = hobbyDao.update(hobby);

        Assert.notNull(updatedHobby.getId(),"update hobby was failed");
    }

    @Test
    public void findAll() throws Exception {
        HobbyDto hobby1 = entityCreator.createNewHobby(hobbyDao);
        HobbyDto hobby2 = entityCreator.createNewHobby(hobbyDao);

        Collection<HobbyDto> hobbies = hobbyDao.findAll();
        Assert.notEmpty(hobbies,"Can't find any hobbies");
    }

    @Test
    public void findAllPersonsWithHobby() throws Exception {
        PersonDto person1 = entityCreator.createNewPerson(personDao, "sixthHb");
        PersonDto person2 = entityCreator.createNewPerson(personDao, "seventhHb");
        HobbyDto hobby1 = entityCreator.createNewHobby(hobbyDao);
        hobbyDao.create(hobby1);
        hobbyDao.addPersonToHobby(hobby1, person1);
        hobbyDao.addPersonToHobby(hobby1, person2);
        Collection<PersonDto> personsWithHobby = hobbyDao.findAllPersonsWithHobby(hobby1);
        Assert.notEmpty(personsWithHobby,"Can't find any persons with specific hobby");
    }

    @Test
    public void addPersonToHobby() throws Exception {
        PersonDto person = entityCreator.createNewPerson(personDao, "fifthHb");
        HobbyDto hobby1 = entityCreator.createNewHobby(hobbyDao);
        Long id = hobbyDao.addPersonToHobby(hobby1, person);
        Assert.notNull(id,"update hobby was failed");
    }
    }