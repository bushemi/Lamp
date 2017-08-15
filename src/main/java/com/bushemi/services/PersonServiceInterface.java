package com.bushemi.services;

import com.bushemi.entities.Person;

/**
 * Created by igor on 16.08.17.
 */
public interface PersonServiceInterface {
    Person createPerson();
    void removePerson(Person person);
    void addFriendShip(Person person, Person friend);


}
