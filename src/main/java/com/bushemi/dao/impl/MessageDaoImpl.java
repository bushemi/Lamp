package com.bushemi.dao.impl;

import com.bushemi.converters.EntityDtoConverter;
import com.bushemi.dao.MessageDao;
import com.bushemi.dao.entity.Message;
import com.bushemi.model.entity.MessageDto;
import com.bushemi.model.entity.PersonDto;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by igor on 01.09.17.
 * useless comment
 */
@Repository
@Transactional
public class MessageDaoImpl implements MessageDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public MessageDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long create(MessageDto entity) {
        Message message = EntityDtoConverter.convert(entity);
        Session session = sessionFactory.getCurrentSession();
        session.merge(message.getPersonFrom());
        session.merge(message.getPersonTo());

        session.save(message);
        return message.getId();
    }

    @Override
    public MessageDto findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Message message = (Message) session.get(Message.class, id);
        return EntityDtoConverter.convert(message);
    }

    @Override
    public void delete(MessageDto entity) {
        Message message = EntityDtoConverter.convert(entity);
        Session session = sessionFactory.getCurrentSession();
        session.delete(message);
    }

    @Override
    public MessageDto update(MessageDto entity) {
        Message message = EntityDtoConverter.convert(entity);
        Session session = sessionFactory.getCurrentSession();
        session.update(message);
        return EntityDtoConverter.convert(message);
    }

    @Override
    public Collection<MessageDto> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Message> messages = new ArrayList();
        messages = session.createQuery("from com.bushemi.dao.entity.Message").list();
        return messages.stream().map(EntityDtoConverter::convert).collect(Collectors.toList());
    }

    @Override
    public Collection<MessageDto> findAllMessagesFrom(PersonDto personDto) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(EntityDtoConverter.convert(personDto));

        Criteria criteria = session.createCriteria(Message.class);
        criteria.add(Restrictions.eq("personFrom.id", personDto.getId()));

        List<Message> messages = criteria.list();

        return messages.stream().map(EntityDtoConverter::convert).collect(Collectors.toList());
    }

    @Override
    public Collection<MessageDto> findAllMessagesFromTo(PersonDto personFrom, PersonDto personTo) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(EntityDtoConverter.convert(personFrom));

        Criteria criteria = session.createCriteria(Message.class);
        criteria.add(Restrictions.eq("personFrom.id", personFrom.getId()));
        criteria.add(Restrictions.eq("personTo.id", personTo.getId()));

        List<Message> messages = criteria.list();

        return messages.stream().map(EntityDtoConverter::convert).collect(Collectors.toList());
    }

    @Override
    public Collection<MessageDto> findAllMessagesTo(PersonDto personDtoTo) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(EntityDtoConverter.convert(personDtoTo));

        Criteria criteria = session.createCriteria(Message.class);
        criteria.add(Restrictions.eq("personTo.id", personDtoTo.getId()));

        List<Message> messages = criteria.list();

        return messages.stream().map(EntityDtoConverter::convert).collect(Collectors.toList());
    }

    @Override
    public Collection<MessageDto> findMessagesByPersonId(long id) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Message.class);

        criteria.add(Restrictions.or(Restrictions.eq("personFrom.id", id)
                ,Restrictions.eq("personTo.id", id)));

        List<Message> messages = criteria.list();
        return messages.stream().map(EntityDtoConverter::convert).collect(Collectors.toList());
    }

}
