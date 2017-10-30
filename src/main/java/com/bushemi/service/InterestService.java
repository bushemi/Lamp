package com.bushemi.service;

import com.bushemi.model.entity.HobbyDto;
import com.bushemi.model.entity.PersonDto;
import com.bushemi.model.entity.PlaceDto;

import java.util.Collection;


public interface InterestService {
    void addPlace(PlaceDto place);
    void addHobby(HobbyDto hobby);
    void addPersonPlace(PersonDto personDto, PlaceDto place);
    void addPersonHobby(PersonDto personDto, HobbyDto hobby);
    Collection<PersonDto> getAllPeopleWithHobby(HobbyDto hobby);
    Collection<PersonDto> getAllPeopleFromPlace(PlaceDto place);
    Collection<PlaceDto> getAllPlaces();
    Collection<HobbyDto> getAllHobbies();


    PlaceDto getPlaceById(long id);
    HobbyDto getHobbyById(long id);
}
