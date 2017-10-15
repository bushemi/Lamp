package com.bushemi.dao.impl;

import com.bushemi.converters.EntityDtoConverter;
import com.bushemi.dao.HobbyDao;
import com.bushemi.dao.entity.Hobby;
import com.bushemi.dao.entity.Person;
import com.bushemi.model.HobbyDto;
import com.bushemi.model.PersonDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class HobbyDaoImpl implements HobbyDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public HobbyDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Long create(HobbyDto entity) {
        Session session = sessionFactory.getCurrentSession();
        Hobby hobby = EntityDtoConverter.convert(entity);

        session.save(hobby);
        return hobby.getId();
    }

    @Override
    public HobbyDto findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Hobby hobby = (Hobby) session.get(Hobby.class, id);
        return EntityDtoConverter.convert(hobby);
    }

    @Override
    public void delete(HobbyDto entity) {
        Session session = sessionFactory.getCurrentSession();
        Hobby hobby = EntityDtoConverter.convert(entity);

        session.delete(hobby);
    }

    @Override
    public HobbyDto update(HobbyDto entity) {
        Session session = sessionFactory.getCurrentSession();
        Hobby hobby = EntityDtoConverter.convert(entity);
        session.update(hobby);
        return EntityDtoConverter.convert(hobby);
    }

    @Override
    public Collection<HobbyDto> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Hobby> hobbies = new ArrayList();
        hobbies = session.createQuery("from com.bushemi.dao.entity.Hobby").list();
        return hobbies.stream().map(EntityDtoConverter::convert).collect(Collectors.toList());
    }

    @Override
    public Long addPersonToHobby(HobbyDto hobbyDto, PersonDto personDto) {
        Hobby hobby = EntityDtoConverter.convert(hobbyDto);
        Person person = EntityDtoConverter.convert(personDto);
        Session session = sessionFactory.getCurrentSession();

        hobby = (Hobby) session.merge(hobby);
        person = (Person) session.merge(person);

        hobby.getPersonsWithHobby().add(person);
        person.getHobbies().add(hobby);

        session.merge(hobby);
        return hobby.getId();
    }

    @Override
    public Collection<PersonDto> findAllPersonsWithHobby(HobbyDto entity) {
        Session session = sessionFactory.getCurrentSession();
        Hobby hobby = EntityDtoConverter.convert(entity);
        hobby = (Hobby) session.get(Hobby.class, hobby.getId());

        return hobby.getPersonsWithHobby().stream().map(EntityDtoConverter::convert).collect(Collectors.toList());
    }
}
