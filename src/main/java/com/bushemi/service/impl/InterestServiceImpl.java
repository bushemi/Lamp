package com.bushemi.service.impl;

import com.bushemi.dao.HobbyDao;
import com.bushemi.dao.PlaceDao;
import com.bushemi.model.entity.HobbyDto;
import com.bushemi.model.entity.PersonDto;
import com.bushemi.model.entity.PlaceDto;
import com.bushemi.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by igor on 28.09.17.
 * useless comment
 */
@Service
public class InterestServiceImpl implements InterestService {
    private final PlaceDao placeDao;
    private final HobbyDao hobbyDao;

    @Autowired
    public InterestServiceImpl(PlaceDao placeDao, HobbyDao hobbyDao) {
        this.placeDao = placeDao;
        this.hobbyDao = hobbyDao;
    }

    @Override
    public void addPlace(PlaceDto place) {
        placeDao.create(place);
    }

    @Override
    public void addHobby(HobbyDto hobby) {
        hobbyDao.create(hobby);
    }

    @Override
    public void addPersonPlace(PersonDto personDto, PlaceDto place) {
        placeDao.addPersonToPlace(personDto, place);
    }

    @Override
    public void addPersonHobby(PersonDto personDto, HobbyDto hobby) {
        hobbyDao.addPersonToHobby(hobby, personDto);
    }

    @Override
    public Collection<PersonDto> getAllPeopleWithHobby(HobbyDto hobby) {
        //
        //find peoples by hobby
        return null;
    }

    @Override
    public Collection<PersonDto> getAllPeopleFromPlace(PlaceDto place) {

        //find peoples by place
        return null;
    }

    @Override
    public Collection<PlaceDto> getAllPlaces() {
        return placeDao.findAll();
    }

    @Override
    public Collection<HobbyDto> getAllHobbies() {
        return hobbyDao.findAll();
    }

    @Override
    public PlaceDto getPlaceById(long id) {
        return placeDao.findById(id);
    }

    @Override
    public HobbyDto getHobbyById(long id) {
        return hobbyDao.findById(id);
    }
}
