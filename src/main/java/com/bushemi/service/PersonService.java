package com.bushemi.service;

import com.bushemi.model.entity.FriendshipDto;
import com.bushemi.model.entity.PersonDto;

import java.time.LocalDate;
import java.util.Collection;


public interface PersonService {

    PersonDto createPerson(String firstName, String lastName, String nickname, LocalDate birthday, String photoUrl);
    PersonDto createPerson(Long id, String firstName, String lastName, String nickname, LocalDate birthday, String photoUrl);
    PersonDto renamePerson(String nickname, String firstName, String lastName );
    PersonDto changeNickname(String oldNickname, String newNickname);

    void removePerson(PersonDto person);
    void addFriendShip(PersonDto person, PersonDto friend);
    Collection<PersonDto> findAllPersons();

    Collection<FriendshipDto> findAllFriendships();

    Collection<PersonDto> findFriendsOf(PersonDto person);
    PersonDto findPersonByNickname(String nickname);

    PersonDto findPersonById(Long id);

    boolean removeFriendship(PersonDto person, PersonDto friend);
}
