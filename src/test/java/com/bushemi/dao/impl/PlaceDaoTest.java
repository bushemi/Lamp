package com.bushemi.dao.impl;

import com.bushemi.dao.PersonDao;
import com.bushemi.dao.PlaceDao;
import com.bushemi.model.PersonDto;
import com.bushemi.model.PlaceDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * Created by igor on 13.10.17.
 * useless comment
 */
@ContextConfiguration(locations = "classpath:app-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PlaceDaoTest {
    @Autowired
    PersonDao personDao;
    @Autowired
    PlaceDao placeDao;

    private EntityCreatorForDaoTests entityCreator;

    @Before
    public void createNewEntityCreator(){
        entityCreator = new EntityCreatorForDaoTests();
    }

    @Test
    public void create() throws Exception {
        PlaceDto place = entityCreator.createNewPlace(placeDao);
        Assert.notNull(place.getId(),"Creating new place was failed");
    }

    @Test
    public void findById() throws Exception {
        PlaceDto place = entityCreator.createNewPlace(placeDao);

        PlaceDto loadedPlace = placeDao.findById(place.getId());
        Assert.notNull(loadedPlace.getId(),"Creating new place was failed");
    }

    @Test(expected = NullPointerException.class)
    public void delete() throws Exception {
        PlaceDto place = entityCreator.createNewPlace(placeDao);
        placeDao.delete(place);

        PlaceDto loadedPlace = placeDao.findById(place.getId());
    }

    @Test
    public void update() throws Exception {
        PlaceDto place = entityCreator.createNewPlace(placeDao);
        place.setDescription("Small cave.");

        PlaceDto updatedPlace = placeDao.update(place);
        Assert.notNull(updatedPlace.getId(),"Updating place was failed");
    }

    @Test
    public void findAll() throws Exception {
        PlaceDto place1 = entityCreator.createNewPlace(placeDao);
        PlaceDto place2 = entityCreator.createNewPlace(placeDao);

        Collection<PlaceDto> places = placeDao.findAll();
        Assert.notEmpty(places,"Can't find any places");
    }

    @Test
    public void findPersonsFromPlace() throws Exception{
        PersonDto first = entityCreator.createNewPerson(personDao, "first!");
        PlaceDto place = entityCreator.createNewPlace(placeDao);
        placeDao.addPersonToPlace(first, place);

        Collection<PersonDto> personsFromPlace = placeDao.findPersonsFromPlace(place);
        Assert.notEmpty(personsFromPlace,"Can't find any persons from this place");
    }

    @Test
    public void addPersonToPlace() throws Exception{
        PersonDto second = entityCreator.createNewPerson(personDao, "second!");
        PlaceDto place = entityCreator.createNewPlace(placeDao);

        placeDao.addPersonToPlace(second, place);
    }
}