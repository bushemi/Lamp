package com.bushemi.service.impl;


import com.bushemi.dao.FriendshipDao;
import com.bushemi.dao.PersonDao;
import com.bushemi.exceptions.UserRegisteredException;
import com.bushemi.model.entity.FriendshipDto;
import com.bushemi.model.entity.PersonDto;
import com.bushemi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by igor on 21.09.17.
 * useless comment
 */
@Service
@Transactional

public class PersonServiceImpl implements PersonService {

    final private FriendshipDao friendshipDao;
    final private PersonDao personDao;

    @Autowired
    public PersonServiceImpl(FriendshipDao friendshipDao, PersonDao personDao) {
        this.friendshipDao = friendshipDao;
        this.personDao = personDao;
    }


    @Override
    public PersonDto createPerson(String firstName, String lastName, String nickname, LocalDate birthday, String photoUrl) {
        PersonDto person = createPerson(null, firstName, lastName, nickname, birthday, photoUrl);
        return person;
    }

    @Override
    public PersonDto createPerson(Long id, String firstName, String lastName, String nickname, LocalDate birthday, String photoUrl) {
        PersonDto person;
        try {
            person = personDao.findByNickname(nickname);

        }catch(NullPointerException npe){

            person = new PersonDto();
            person.setId(id);
            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setNickname(nickname);
            person.setBirthday(birthday);
            person.setPhotoURL(photoUrl);
            //create dto
            personDao.update(person);
            return person;
        }

        throw new UserRegisteredException("That nickname is already registered");
    }

    @Override
    public PersonDto renamePerson(String nickname, String firstName, String lastName) {
        PersonDto person = personDao.findByNickname(nickname);
        person.setLastName(lastName);
        person.setFirstName(firstName);
        personDao.update(person);
        return person;
    }

    @Override
    public PersonDto changeNickname(String oldNickname, String newNickname) {
        PersonDto person = personDao.findByNickname(oldNickname);
        person.setNickname(newNickname);
        personDao.update(person);
        return null;
    }

    @Override
    public void removePerson(PersonDto person) {
        personDao.delete(person);
        //delete dto

    }

    @Override
    public void addFriendShip(PersonDto person, PersonDto friend) {
        FriendshipDto friendship = null;
        if (!friendshipDao.isFriends(person, friend)){
            friendship = new FriendshipDto();
            friendship.setFriend(person);
            friendship.setPerson(friend);
            friendship.setFriendFrom(LocalDate.now());
            friendshipDao.create(friendship);
        }

    }

    @Override
    public Collection<PersonDto> findAllPersons() {
        Collection<PersonDto> persons =  new ArrayList<>();
        persons.addAll(personDao.findAll());
        return persons;
    }

    @Override
    public Collection<FriendshipDto> findAllFriendships() {
        Collection<FriendshipDto> friendships =  new ArrayList<>();
        friendships.addAll(friendshipDao.findAll());
        return friendships;
    }
    @Override
    public Collection<PersonDto> findFriendsOf(PersonDto person) {
        Collection<PersonDto> persons =  new ArrayList<>();
        persons.addAll(personDao.findFriendsOf(person));
        return persons;
    }

    @Override
    public PersonDto findPersonByNickname(String nickname) {
        return personDao.findByNickname(nickname);
    }

    @Override
    public PersonDto findPersonById(Long id) {
        return personDao.findById(id);
    }

    @Override
    public boolean removeFriendship(PersonDto person, PersonDto friend) {
        FriendshipDto friendship = new FriendshipDto();
        friendship.setFriend(person);
        friendship.setPerson(friend);
        friendshipDao.delete(friendship);
        return false;
    }

}
