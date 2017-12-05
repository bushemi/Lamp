package com.bushemi.service.impl;

import com.bushemi.dao.*;
import com.bushemi.model.entity.*;
import com.bushemi.service.EntityFindingByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by igor on 24.10.17.
 *
 * @Version 1.0
 * This service helps to find entities when we know person id
 */
@Service
public class EntityFindingByIdServiceImpl implements EntityFindingByIdService {
    private final FriendshipDao friendshipDao;
    private final PersonDao personDao;
    private final PlaceDao placeDao;
    private final HobbyDao hobbyDao;
    private final PostDao postDao;
    private final MessageDao messageDao;

    @Autowired
    public EntityFindingByIdServiceImpl(FriendshipDao friendshipDao, PersonDao personDao, PlaceDao placeDao, HobbyDao hobbyDao, PostDao postDao, MessageDao messageDao) {
        this.friendshipDao = friendshipDao;
        this.personDao = personDao;
        this.placeDao = placeDao;
        this.hobbyDao = hobbyDao;
        this.postDao = postDao;
        this.messageDao = messageDao;
    }

    @Override
    public PersonDto getPersonDtoById(long id) {
        return personDao.findById(id);
    }

    @Override
    @Transactional
    public Collection<PlaceDto> getPlacesDtoByPersonId(long id) {
        return placeDao.findPlacesByPersonId(id);
    }

    @Override
    @Transactional
    public Collection<PostDto> getPostsDtoByOwnerId(long id) {
        return postDao.findPostsByOwnerId(id);
    }

    @Override
    @Transactional
    public Collection<HobbyDto> getHobbiesDtoByPersonId(long id) {
        return hobbyDao.findHobbiesByPersonId(id);
    }

    @Override
    @Transactional
    public Collection<FriendshipDto> getFriendshipsDtoByPersonId(long id) {
        return friendshipDao.findFriendshipsByPersonId(id);
    }

    @Override
    @Transactional
    public Collection<MessageDto> getMessagesDtoByPersonId(long id) {
        return messageDao.findMessagesByPersonId(id);
    }

    @Override
    @Transactional
    public PostDto getPostsDtoById(long id) {
        return postDao.findById(id);
    }
}
