package com.bushemi.converters;

import com.bushemi.dao.entity.*;
import com.bushemi.model.entity.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by igor on 21.09.17.
 * useless comment
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EntityDtoConverter.class)


public class EntityDtoConverterTest {

    private static final Long ID_TO_ALL_ENTITIES = 666l;
    private static final LocalDate LOCAL_DATE = LocalDate.of(2012, 5, 31);
    private static final String FIRST_NAME = "Tony";
    private static final String LAST_NAME = "Stark";
    private static final String NICKNAME = "Iron_Man";
    private static final String HOBBY_TITLE = "alcohol";
    private static final String HOBBY_DESCRIPTION = "I like to drink alcohol";
    private static final String MSG_CONTENT = "I love u";
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.of(2012, 5, 31, 22, 0);
    private static final String PLACE_DESCRIPTION = "small comfortable house with garden";
    private static final String PLACE_TITLE = "village house";
    private static final double PLACE_LATITUDE = 48.319732;
    private static final double PLACE_LONGITUDE = 35.072183;
    private static final String POST_TITLE = "love";
    private static final String POST_CONTENT = "I love pepsi";
    private static final String MY_PHOTO = "nudes";


    private Person getPersonDao(){
        Person person = new Person();
        person.setId(ID_TO_ALL_ENTITIES);
        person.setBirthday(LOCAL_DATE);
        person.setFirstName(FIRST_NAME);
        person.setLastName(LAST_NAME);
        person.setNickname(NICKNAME);
        person.setPhotoURL(MY_PHOTO);
        return  person;
    }
    private PersonDto getPersonDto(){
        PersonDto person = new PersonDto();
        person.setId(ID_TO_ALL_ENTITIES);
        person.setBirthday(LOCAL_DATE);
        person.setFirstName(FIRST_NAME);
        person.setLastName(LAST_NAME);
        person.setNickname(NICKNAME);
        person.setPhotoURL(MY_PHOTO);
        return  person;
    }

    private Friendship getFriendshipDao(){
        Friendship friendship = new Friendship();
        friendship.setPerson(getPersonDao());
        friendship.setFriend(getPersonDao());
        friendship.setFriendFrom(LOCAL_DATE);
        return  friendship;
    }
    private FriendshipDto getFriendshipDto(){
        FriendshipDto friendship = new FriendshipDto();
        friendship.setPerson(getPersonDto());
        friendship.setFriend(getPersonDto());
        friendship.setFriendFrom(LOCAL_DATE);
        return  friendship;
    }

    private Hobby getHobbyDao(){
        Hobby hobby = new Hobby();
        hobby.setId(ID_TO_ALL_ENTITIES);
        hobby.setTitle(HOBBY_TITLE);
        hobby.setDescription(HOBBY_DESCRIPTION);
        return  hobby;
    }
    private HobbyDto getHobbyDto(){
        HobbyDto hobby = new HobbyDto();
        hobby.setId(ID_TO_ALL_ENTITIES);
        hobby.setTitle(HOBBY_TITLE);
        hobby.setDescription(HOBBY_DESCRIPTION);
        return  hobby;
    }

    private Message getMessageDao(){
        Message message = new Message();
        message.setId(ID_TO_ALL_ENTITIES);
        message.setPersonFrom(getPersonDao());
        message.setPersonTo(getPersonDao());
        message.setContent(MSG_CONTENT);
        message.setTimeSent(LOCAL_DATE_TIME);
        return  message;
    }
    private MessageDto getMessageDto(){
        MessageDto message = new MessageDto();
        message.setId(ID_TO_ALL_ENTITIES);
        message.setPersonFrom(getPersonDto());
        message.setPersonTo(getPersonDto());
        message.setContent(MSG_CONTENT);
        message.setTimeSent(LOCAL_DATE_TIME);
        return  message;
    }

    private Place getPlaceDao(){
        Place place = new Place();
        place.setId(ID_TO_ALL_ENTITIES);
        place.setDescription(PLACE_DESCRIPTION);
        place.setTitle(PLACE_TITLE);
        place.setLatitude(PLACE_LATITUDE);
        place.setLongitude(PLACE_LONGITUDE);
        return  place;
    }
    private PlaceDto getPlaceDto(){
        PlaceDto place = new PlaceDto();
        place.setId(ID_TO_ALL_ENTITIES);
        place.setDescription(PLACE_DESCRIPTION);
        place.setTitle(PLACE_TITLE);
        place.setLatitude(PLACE_LATITUDE);
        place.setLongitude(PLACE_LONGITUDE);
        return  place;
    }

    private Post getPostDao(){
        Post post = new Post();
        post.setId(ID_TO_ALL_ENTITIES);
        post.setOwner(getPersonDao());
        post.setTitle(POST_TITLE);
        post.setContent(POST_CONTENT);
        post.setPlaceTime(LOCAL_DATE_TIME);
        return  post;
    }
    private PostDto getPostDto(){
        PostDto post = new PostDto();
        post.setId(ID_TO_ALL_ENTITIES);
        post.setOwner(getPersonDto());
        post.setTitle(POST_TITLE);
        post.setContent(POST_CONTENT);
        post.setPlaceTime(LOCAL_DATE_TIME);
        return  post;
    }

    private PostLikes getPostLikesDao(){
        PostLikes postLikes = new PostLikes();
        postLikes.setLiker(getPersonDao());
        postLikes.setPost(getPostDao());
        return  postLikes;
    }
    private PostLikesDto getPostLikesDto(){
        PostLikesDto postLikes = new PostLikesDto();
        postLikes.setLiker(getPersonDto());
        postLikes.setPost(getPostDto());
        return  postLikes;
    }

    @Test
    public void convertPersonDtoToPersonDao() throws Exception {
        Assert.assertEquals(getPersonDao(), EntityDtoConverter.convert(getPersonDto()));
    }

    @Test
    public void convertPersonDaoToPersonDto() throws Exception {
        Assert.assertEquals(getPersonDto(), EntityDtoConverter.convert(getPersonDao()));
    }


    @Test
    public void convertFriendshipDtoToFriendshipDao() throws Exception {

        Friendship friendshipDao = getFriendshipDao();
        Friendship convertFriendship = EntityDtoConverter.convert(getFriendshipDto());
        System.out.println(friendshipDao.equals(convertFriendship));
        Assert.assertEquals(friendshipDao, convertFriendship);

    }

    @Test
    public void convertFriendshipDaoToFriendshipDto() throws Exception {
        Assert.assertEquals(getFriendshipDto(), EntityDtoConverter.convert(getFriendshipDao()));
    }

    @Test
    public void convertHobbyDtoToHobbyDao() throws Exception {
        Assert.assertEquals(getHobbyDao(), EntityDtoConverter.convert(getHobbyDto()));
    }

    @Test
    public void convertHobbyDaoToHobbyDto() throws Exception {
        Assert.assertEquals(getHobbyDto(), EntityDtoConverter.convert(getHobbyDao()));
    }

    @Test
    public void convertMessageDtoToMessageDao() throws Exception {
        Assert.assertEquals(getMessageDao(), EntityDtoConverter.convert(getMessageDto()));
    }

    @Test
    public void convertMessageDaoToMessageDto() throws Exception {
        Assert.assertEquals(getMessageDto(), EntityDtoConverter.convert(getMessageDao()));
    }

    @Test
    public void convertPlaceDtoToPlaceDao() throws Exception {
        Assert.assertEquals(getPlaceDao(), EntityDtoConverter.convert(getPlaceDto()));
    }

    @Test
    public void convertPlaceDaoToPlaceDto() throws Exception {
        Assert.assertEquals(getPlaceDto(), EntityDtoConverter.convert(getPlaceDao()));
    }
    @Test
    public void convertPostDtoToPostDao() throws Exception {
        Assert.assertEquals(getPostDao(), EntityDtoConverter.convert(getPostDto()));
    }

    @Test
    public void convertPostDaoToPostDto() throws Exception {
        Assert.assertEquals(getPostDto(), EntityDtoConverter.convert(getPostDao()));
    }
    @Test
    public void convertPostLikesDtoToPostLikesDao() throws Exception {
        Assert.assertEquals(getPostLikesDao(), EntityDtoConverter.convert(getPostLikesDto()));
    }

    @Test
    public void convertPostLikesDaoToPostLikesDto() throws Exception {
        Assert.assertEquals(getPostLikesDto(), EntityDtoConverter.convert(getPostLikesDao()));
    }

}