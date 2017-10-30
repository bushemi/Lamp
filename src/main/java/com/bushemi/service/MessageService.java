package com.bushemi.service;

import com.bushemi.model.entity.MessageDto;
import com.bushemi.model.entity.PersonDto;

import java.util.Collection;

/**
 * Created by igor on 10.10.17.
 * useless comment
 */
public interface MessageService {

    MessageDto createMessage(PersonDto personFrom, PersonDto personTo, String text);
    Collection<MessageDto> findAllMessagesFromTo(PersonDto personFrom, PersonDto personTo);

}
