package com.bushemi.services;

import com.bushemi.entities.Hobby;
import com.bushemi.entities.Person;
import com.bushemi.entities.Place;

import java.util.Collection;

/**
 * Created by igor on 16.08.17.
 */
public interface InterestServiceInterface {
    void addPlace(Place place);
    void addHobby(Hobby hobby);
    Collection<Person> getAllPeopleWithHobby(Hobby hobby);
    Collection<Person> getAllPeopleFromPlace(Place place);

}
