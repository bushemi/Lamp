package com.bushemi.service.impl;

import com.bushemi.dao.MessageDao;
import com.bushemi.model.entity.MessageDto;
import com.bushemi.model.entity.PersonDto;
import com.bushemi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by igor on 10.10.17.
 * useless comment
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDao messageDao;

    @Override
    public MessageDto createMessage(PersonDto personFrom, PersonDto personTo, String text) {
        MessageDto msg = new MessageDto();
        msg.setContent(text);
        msg.setPersonFrom(personFrom);
        msg.setPersonTo(personTo);
        msg.setTimeSent(LocalDateTime.now());

        Long id = messageDao.create(msg);
        msg.setId(id);
        return msg;
    }

    @Override
    public Collection<MessageDto> findAllMessagesFromTo(PersonDto personFrom, PersonDto personTo) {
        Collection<MessageDto> messages = new ArrayList<>();
        messages.addAll(messageDao.findAllMessagesFromTo(personFrom, personTo));
        return messages;
    }
}
