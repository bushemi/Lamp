package com.bushemi.dao.impl;

import com.bushemi.dao.*;
import com.bushemi.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by igor on 12.10.17.
 * useless comment
 */
public class EntityCreatorForDaoTests {

    public PersonDto createNewPerson(PersonDao personDao, String nickname) {
        PersonDto person = new PersonDto();
        person.setFirstName("Jesus");
        person.setLastName("Christ");
        person.setNickname(nickname);
        person.setBirthday(LocalDate.of(0000,01,01));
        //create dto
        Long id = personDao.create(person);
        person.setId(id);
        return person;
    }

    public PostDto createNewPost(PostDao postDao, PersonDto owner) {
        PostDto post = new PostDto();
        post.setTitle("Title");
        post.setContent("My post begins from uppercase letter.");
        post.setOwner(owner);
        post.setPlaceTime(LocalDateTime.now());
        Long postId = postDao.create(post);
        //create dto
        post.setId(postId);
        return post;
    }

    public UserDto createNewUser(UserDao userDao, String login, PersonDto person) {
        UserDto user =  new UserDto();
        user.setLogin(login);
        user.setPerson(person);
        user.setPassword("123456789");
        user.setEmail("12345@fail.org");
        Long id = userDao.create(user);
        user.setId(id);
        return user;
    }

    public PlaceDto createNewPlace(PlaceDao placeDao) {
        PlaceDto place = new PlaceDto();
        place.setTitle("house");
        place.setDescription("Big house");
        place.setLatitude(11.111111);
        place.setLongitude(11.111111);
        Long id = placeDao.create(place);
        place.setId(id);
        return place;
    }

    public FriendshipDto createNewFriendship(FriendshipDao friendshipDao, PersonDto first, PersonDto second) {
        FriendshipDto friendship = new FriendshipDto();
        friendship.setPerson(first);
        friendship.setFriend(second);
        friendship.setFriendFrom(LocalDate.now());
        friendshipDao.create(friendship);
        return friendship;
    }

    public HobbyDto createNewHobby(HobbyDao hobbyDao) {
        HobbyDto hobby = new HobbyDto();
        hobby.setTitle("Swimming");
        hobby.setDescription("In the my pool");

        Long id = hobbyDao.create(hobby);
        hobby.setId(id);
        return hobby;

    }

    public MessageDto createNewMessage(MessageDao messageDao, PersonDto personFrom, PersonDto personTo) {
        MessageDto msg = new MessageDto();
        msg.setPersonFrom(personFrom);
        msg.setPersonTo(personTo);
        msg.setContent("one two three");
        msg.setTimeSent(LocalDateTime.now());

        Long id = messageDao.create(msg);
        msg.setId(id);
        return msg;
    }
}
