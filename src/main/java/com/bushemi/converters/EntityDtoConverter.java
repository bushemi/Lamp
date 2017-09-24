package com.bushemi.converters;


import com.bushemi.dao.entity.*;
import com.bushemi.model.*;

import javax.persistence.Converter;



public final class EntityDtoConverter {

	private EntityDtoConverter() {
		//private default constructor for utility class
	}

	public static Person convert(PersonDto personDto) {
		Person person = new Person();
		person.setId(personDto.getId());
		person.setFirstName(personDto.getFirstName());
		person.setLastName(personDto.getLastName());
		person.setNickname(personDto.getNickname());
		person.setBirthday(personDto.getBirthday());
		return  person;
	}

	public static PersonDto convert(Person personDao) {
		PersonDto person = new PersonDto();
		person.setId(personDao.getId());
		person.setFirstName(personDao.getFirstName());
		person.setLastName(personDao.getLastName());
		person.setNickname(personDao.getNickname());
		person.setBirthday(personDao.getBirthday());
		return  person;
	}


	public static FriendshipDto convert(Friendship friendshipDao) {
		FriendshipDto friendship = new FriendshipDto();
		friendship.setFriend(EntityDtoConverter.convert(friendshipDao.getFriend()));
		friendship.setPerson(EntityDtoConverter.convert(friendshipDao.getPerson()));
		friendship.setFriendFrom(friendshipDao.getFriendFrom());
		return  friendship;
	}

	public static Friendship convert(FriendshipDto friendshipDto) {
		Friendship friendship = new Friendship();
		friendship.setFriend(EntityDtoConverter.convert(friendshipDto.getFriend()));
		friendship.setPerson(EntityDtoConverter.convert(friendshipDto.getPerson()));
		friendship.setFriendFrom(friendshipDto.getFriendFrom());
		return  friendship;
	}

	public static HobbyDto convert(Hobby hobbyDao) {
		HobbyDto hobby = new HobbyDto();
		hobby.setId(hobbyDao.getId());
		hobby.setTitle(hobbyDao.getTitle());
		hobby.setDescription(hobbyDao.getDescription());
		return hobby;
	}

	public static Hobby convert(HobbyDto hobbyDto) {
		Hobby hobby = new Hobby();
		hobby.setId(hobbyDto.getId());
		hobby.setTitle(hobbyDto.getTitle());
		hobby.setDescription(hobbyDto.getDescription());
		return hobby;
	}

	public static Message convert(MessageDto messageDto) {
		Message message = new Message();
		message.setId(messageDto.getId());
		message.setTimeSent(messageDto.getTimeSent());
		message.setContent(messageDto.getContent());
		message.setPersonFrom(EntityDtoConverter.convert(messageDto.getPersonFrom()));
		message.setPersonTo(EntityDtoConverter.convert(messageDto.getPersonTo()));
		return message;
	}

	public static MessageDto convert(Message messageDao) {
		MessageDto message = new MessageDto();
		message.setId(messageDao.getId());
		message.setTimeSent(messageDao.getTimeSent());
		message.setContent(messageDao.getContent());
		message.setPersonFrom(EntityDtoConverter.convert(messageDao.getPersonFrom()));
		message.setPersonTo(EntityDtoConverter.convert(messageDao.getPersonTo()));
		return message;
	}

	public static Place convert(PlaceDto placeDto) {
		Place place = new Place();
		place.setId(placeDto.getId());
		place.setTitle(placeDto.getTitle());
		place.setDescription(placeDto.getDescription());
		place.setLongitude(placeDto.getLongitude());
		place.setLatitude(placeDto.getLatitude());
		return place;
	}

	public static PlaceDto convert(Place placeDao) {
		PlaceDto place = new PlaceDto();
		place.setId(placeDao.getId());
		place.setTitle(placeDao.getTitle());
		place.setDescription(placeDao.getDescription());
		place.setLongitude(placeDao.getLongitude());
		place.setLatitude(placeDao.getLatitude());
		return place;
	}

	public static Post convert(PostDto postDto) {
		Post post = new Post();
		post.setId(postDto.getId());
		post.setPlaceTime(postDto.getPlaceTime());
		post.setContent(postDto.getContent());
		post.setTitle(postDto.getTitle());
		post.setPerson(EntityDtoConverter.convert(postDto.getPerson()));
		return post;
	}

	public static PostDto convert(Post postDao) {
		PostDto post = new PostDto();
		post.setId(postDao.getId());
		post.setPlaceTime(postDao.getPlaceTime());
		post.setContent(postDao.getContent());
		post.setTitle(postDao.getTitle());
		post.setPerson(EntityDtoConverter.convert(postDao.getPerson()));
		return post;
	}

	public static PostLikes convert(PostLikesDto postLikesDto) {
		PostLikes postLikes = new PostLikes();
		postLikes.setPost(EntityDtoConverter.convert(postLikesDto.getPost()));
		postLikes.setLiker(EntityDtoConverter.convert(postLikesDto.getLiker()));
		return postLikes;
	}

	public static PostLikesDto convert(PostLikes postLikesDao) {
		PostLikesDto postLikes = new PostLikesDto();
		postLikes.setPost(EntityDtoConverter.convert(postLikesDao.getPost()));
		postLikes.setLiker(EntityDtoConverter.convert(postLikesDao.getLiker()));
		return postLikes;
	}
}