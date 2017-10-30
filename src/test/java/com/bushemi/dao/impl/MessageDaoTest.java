package com.bushemi.dao.impl;

import com.bushemi.dao.MessageDao;
import com.bushemi.dao.PersonDao;
import com.bushemi.model.entity.MessageDto;
import com.bushemi.model.entity.PersonDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * Created by igor on 13.10.17.
 * useless comment
 */
@ContextConfiguration(locations = "classpath:app-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDaoTest {
    @Autowired
    private PersonDao personDao;
    @Autowired
    private MessageDao messageDao;

    private EntityCreatorForDaoTests entityCreator;

    @Before
    public void createNewEntityCreator(){
        entityCreator = new EntityCreatorForDaoTests();
    }

    @Test
    public void create() throws Exception {
        PersonDto person1 = entityCreator.createNewPerson(personDao, "firstMsg");
        PersonDto person2 = entityCreator.createNewPerson(personDao, "secondMsg");
        MessageDto message = entityCreator.createNewMessage(messageDao, person1, person2);

        Assert.notNull(message.getId(),"Creating new message was failed");
    }

    @Test
    public void findById() throws Exception {
        PersonDto person1 = entityCreator.createNewPerson(personDao, "thirdMsg");
        PersonDto person2 = entityCreator.createNewPerson(personDao, "fourthMsg");
        MessageDto message = entityCreator.createNewMessage(messageDao, person1, person2);
        MessageDto message2 = entityCreator.createNewMessage(messageDao, person1, person2);

        MessageDto loadedMsg = messageDao.findById(message.getId());
        Assert.notNull(loadedMsg.getId(),"Loading message by id was failed");
    }

    @Test(expected = NullPointerException.class)
    public void delete() throws Exception {
        PersonDto person1 = entityCreator.createNewPerson(personDao, "fifthMsg");
        PersonDto person2 = entityCreator.createNewPerson(personDao, "sixthMsg");
        MessageDto message = entityCreator.createNewMessage(messageDao, person1, person2);

        messageDao.delete(message);
        MessageDto loadedMsg = messageDao.findById(message.getId());
    }

    @Test
    public void update() throws Exception {
        PersonDto person1 = entityCreator.createNewPerson(personDao, "seventhMsg");
        PersonDto person2 = entityCreator.createNewPerson(personDao, "eigthMsg");
        MessageDto message = entityCreator.createNewMessage(messageDao, person1, person2);
        message.setContent("Elvis is alive!");

        MessageDto updatedMessage = messageDao.update(message);
        Assert.notNull(updatedMessage.getId(),"update message was failed");


    }

    @Test
    public void findAll() throws Exception {
        PersonDto person1 = entityCreator.createNewPerson(personDao, "ninthMsg");
        PersonDto person2 = entityCreator.createNewPerson(personDao, "tenthMsg");
        MessageDto message1 = entityCreator.createNewMessage(messageDao, person1, person2);
        MessageDto message2 = entityCreator.createNewMessage(messageDao, person1, person2);

        Collection<MessageDto> messages = messageDao.findAll();
        Assert.notEmpty(messages,"Can't find any messages");
    }

    @Test
    public void findAllMessagesFrom() throws Exception {
        PersonDto person1 = entityCreator.createNewPerson(personDao, "eleventhMsg");
        PersonDto person2 = entityCreator.createNewPerson(personDao, "twelfthMsg");
        MessageDto message = entityCreator.createNewMessage(messageDao, person1, person2);

        Collection<MessageDto> messages = messageDao.findAllMessagesFrom(person1);
        Assert.notEmpty(messages,"Can't find any messages from person1");
    }

    @Test
    public void findAllMessagesFromTo() throws Exception {
        PersonDto person1 = entityCreator.createNewPerson(personDao, "thirteenthMsg");
        PersonDto person2 = entityCreator.createNewPerson(personDao, "fourteenthMsg");
        MessageDto message = entityCreator.createNewMessage(messageDao, person1, person2);

        Collection<MessageDto> messages = messageDao.findAllMessagesFromTo(person1, person2);
        Assert.notEmpty(messages,"Can't find any messages from person1");
    }

    @Test
    public void findAllMessagesToPerson1() throws Exception {
        PersonDto person1 = entityCreator.createNewPerson(personDao, "fifteenthMsg");
        PersonDto person2 = entityCreator.createNewPerson(personDao, "sixteenthMsg");
        MessageDto message = entityCreator.createNewMessage(messageDao, person1, person2);

        Collection<MessageDto> messages = messageDao.findAllMessagesTo(person1);
        if (!messages.isEmpty()){throw  new AssertionError("Was founded wrong message/messages");}
    }

    @Test
    public void findAllMessagesToPerson2() throws Exception {
        PersonDto person1 = entityCreator.createNewPerson(personDao, "seventeenthMsg");
        PersonDto person2 = entityCreator.createNewPerson(personDao, "eighteenthMsg");
        MessageDto message = entityCreator.createNewMessage(messageDao, person1, person2);

        Collection<MessageDto> messages = messageDao.findAllMessagesTo(person2);
        Assert.notEmpty(messages,"Can't find any messages from person1");
    }

}