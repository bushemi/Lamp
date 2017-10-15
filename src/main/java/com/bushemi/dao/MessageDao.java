package com.bushemi.dao;


import com.bushemi.model.MessageDto;
import com.bushemi.model.PersonDto;

import java.util.Collection;

/**
 * Created by igor on 01.09.17.
 * useless comment
 */
public interface MessageDao extends CrudOperationsInterface<MessageDto> {
    Collection<MessageDto> findAllMessagesFrom(PersonDto personDto);
    Collection<MessageDto> findAllMessagesFromTo(PersonDto personDtoFrom, PersonDto personDtoTo);
    Collection<MessageDto> findAllMessagesTo(PersonDto personDtoTo);
}
