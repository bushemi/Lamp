package com.bushemi.dao;


import com.bushemi.model.entity.MessageDto;
import com.bushemi.model.entity.PersonDto;

import java.util.Collection;

/**
 * Created by igor on 01.09.17.
 * useless comment
 */
public interface MessageDao extends CrudOperationsInterface<MessageDto> {
    Collection<MessageDto> findAllMessagesFrom(PersonDto personDto);
    Collection<MessageDto> findAllMessagesFromTo(PersonDto personDtoFrom, PersonDto personDtoTo);
    Collection<MessageDto> findAllMessagesTo(PersonDto personDtoTo);

    Collection<MessageDto> findMessagesByPersonId(long id);
}
