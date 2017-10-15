package com.bushemi.dao;


import com.bushemi.model.PersonDto;
import com.bushemi.model.PlaceDto;

import java.util.Collection;

/**
 * Created by igor on 01.09.17.
 * useless comment
 */
public interface PlaceDao extends CrudOperationsInterface<PlaceDto> {

    Collection<PersonDto> findPersonsFromPlace(PlaceDto placeDto);
    void addPersonToPlace(PersonDto personDto, PlaceDto placeDto);


}
