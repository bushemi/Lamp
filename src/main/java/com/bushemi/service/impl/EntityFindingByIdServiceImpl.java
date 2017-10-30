package com.bushemi.service.impl;

import com.bushemi.dao.*;
import com.bushemi.model.entity.*;
import com.bushemi.service.EntityFindingByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by igor on 24.10.17.
 *
 * @Version 1.0
 * This service helps to find entities when we know person id
 */
@Service
public class EntityFindingByIdServiceImpl implements EntityFindingByIdService {
    @Autowired
    private FriendshipDao friendshipDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private PlaceDao placeDao;
    @Autowired
    private HobbyDao hobbyDao;
    @Autowired
    private PostDao postDao;
    @Autowired
    private MessageDao messageDao;

    @Override
    public PersonDto getPersonDtoById(long id) {
        return personDao.findById(id);
    }

    @Override
    public Collection<PlaceDto> getPlacesDtoByPersonId(long id) {
        return placeDao.findPlacesByPersonId(id);
    }

    @Override
    public Collection<PostDto> getPostsDtoByOwnerId(long id) {
        return postDao.findPostsByOwnerId(id);
    }

    @Override
    public Collection<HobbyDto> getHobbiesDtoByPersonId(long id) {
        return hobbyDao.findHobbiesByPersonId(id);
    }

    @Override
    public Collection<FriendshipDto> getFriendshipsDtoByPersonId(long id) {
        return friendshipDao.findFriendshipsByPersonId(id);
    }

    @Override
    public Collection<MessageDto> getMessagesDtoByPersonId(long id) {
        return messageDao.findMessagesByPersonId(id);
    }

    @Override
    public PostDto getPostsDtoById(long id) {
        return postDao.findById(id);
    }
}
