package com.bushemi.dao;


import com.bushemi.model.entity.HobbyDto;
import com.bushemi.model.entity.PersonDto;

import java.util.Collection;

/**
 * Created by igor on 01.09.17.
 * useless comment
 */
public interface HobbyDao extends CrudOperationsInterface<HobbyDto>{


    HobbyDto findById(long id);
    void delete(HobbyDto entity);
    HobbyDto update(HobbyDto entity);
    Collection<HobbyDto> findAll();
    Long addPersonToHobby(HobbyDto hobbyDto, PersonDto personDto);
    Collection<PersonDto> findAllPersonsWithHobby(HobbyDto entity);

    Collection<HobbyDto> findHobbiesByPersonId(long id);
}
