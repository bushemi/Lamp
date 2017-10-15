package com.bushemi.dao.impl;

import com.bushemi.converters.EntityDtoConverter;
import com.bushemi.dao.PlaceDao;
import com.bushemi.dao.entity.Person;
import com.bushemi.dao.entity.Place;
import com.bushemi.model.PersonDto;
import com.bushemi.model.PlaceDto;
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
public class PlaceDaoImpl implements PlaceDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public PlaceDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long create(PlaceDto entity) {
        Place place = EntityDtoConverter.convert(entity);
        Session session = sessionFactory.getCurrentSession();
        session.save(place);
        return place.getId();
    }

    @Override
    public PlaceDto findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Place place = (Place) session.get(Place.class, id);
        return EntityDtoConverter.convert(place);
    }

    @Override
    public void delete(PlaceDto entity) {
        Place place = EntityDtoConverter.convert(entity);
        Session session = sessionFactory.getCurrentSession();
        session.delete(place);
    }

    @Override
    public PlaceDto update(PlaceDto entity) {
        Place place = EntityDtoConverter.convert(entity);
        Session session = sessionFactory.getCurrentSession();
        session.update(place);
        return EntityDtoConverter.convert(place);
    }

    @Override
    public Collection<PlaceDto> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Place> places = new ArrayList();
        places = session.createQuery("from com.bushemi.dao.entity.Place").list();
        return places.stream().map(EntityDtoConverter::convert).collect(Collectors.toList());
    }

    @Override
    public Collection<PersonDto> findPersonsFromPlace(PlaceDto placeDto) {
        Place place = EntityDtoConverter.convert(placeDto);
        Session session = sessionFactory.getCurrentSession();
        place = (Place) session.get(Place.class, place.getId());
        return place.getPersonsFromPlace().stream().map(EntityDtoConverter::convert).collect(Collectors.toList());
    }

    @Override
    public void addPersonToPlace(PersonDto personDto, PlaceDto placeDto) {
        Session session = sessionFactory.getCurrentSession();
        Place place = EntityDtoConverter.convert(placeDto);
        Person person = EntityDtoConverter.convert(personDto);
        place = (Place) session.merge(place);
        person = (Person) session.merge(person);

        place.getPersonsFromPlace().add(person);
        person.getPlaces().add(place);

        session.merge(place);
    }
}
