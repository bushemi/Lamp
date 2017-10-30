package com.bushemi.dao.impl;

import com.bushemi.converters.EntityDtoConverter;
import com.bushemi.dao.PersonDao;
import com.bushemi.dao.entity.Person;
import com.bushemi.dao.entity.Post;
import com.bushemi.model.entity.PersonDto;
import com.bushemi.model.entity.PostDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by igor on 01.09.17.
 * useless comment
 */
@Repository
@Transactional
public class PersonDaoImpl implements PersonDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long create(PersonDto entity) {
        Person person = EntityDtoConverter.convert(entity);
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
        return person.getId();
    }

    @Override
    public PersonDto findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = (Person) session.get(Person.class, id);
        return EntityDtoConverter.convert(person);
    }

    @Override
    public void delete(PersonDto entity) {
        Session session = sessionFactory.getCurrentSession();
        Person person = EntityDtoConverter.convert(entity);
        session.delete(person);
    }

    @Override
    public PersonDto update(PersonDto entity) {
        Session session = sessionFactory.getCurrentSession();
        Person person = EntityDtoConverter.convert(entity);
        person = (Person) session.merge(person);
        return EntityDtoConverter.convert(person);
    }

    @Override
    public Collection<PersonDto> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Person> persons;
        persons = session.createQuery("from com.bushemi.dao.entity.Person").list();
        return persons.stream().map(EntityDtoConverter::convert).collect(Collectors.toList());
    }

    @Override
    public void createLike(PersonDto personDto, PostDto postDto) {
        Session session = sessionFactory.getCurrentSession();
        Person person = (Person) session.get(Person.class, personDto.getId());
        Post post = (Post) session.get(Post.class, postDto.getId());
//        Person person = EntityDtoConverter.convert(personDto);
//        Post post = EntityDtoConverter.convert(postDto);
        person.getLikes().add(post);
        post.getPostLikers().add(person);
        session.merge(person);
    }



    @Override
    public PersonDto findByNickname(String nickname) {
		Person person = (Person) sessionFactory.getCurrentSession()
				.createQuery("select p from com.bushemi.dao.entity.Person p where p.nickname = :nickname")
				.setParameter("nickname", nickname)
				.uniqueResult();

		return EntityDtoConverter.convert(person);
    }

    @Override
    public Collection<PostDto> findLikes(PersonDto personDto) {
        Person person = (Person) sessionFactory.getCurrentSession().get(Person.class, personDto.getId());
        return person.getLikes().stream().map(EntityDtoConverter::convert).collect(Collectors.toList());
    }

    @Override
    public Collection<PersonDto> findFriendsOf(PersonDto person) {
        Session session = sessionFactory.getCurrentSession();
        Person person1 = EntityDtoConverter.convert(person);
        session.merge(person);
        Set<PersonDto> personDtoSet = new TreeSet<>();
        person1.getFriendships().forEach((e)-> {
            personDtoSet.add(EntityDtoConverter.convert(e.getPerson()));
        });

        return personDtoSet;
    }

    @Override
    public void disLike(PersonDto personDto, PostDto postDto) {
        Session session = sessionFactory.getCurrentSession();
//        Person person = EntityDtoConverter.convert(personDto);
//        Post post = EntityDtoConverter.convert(postDto);
        Person person = (Person) session.get(Person.class, personDto.getId());
        Post post = (Post) session.get(Post.class, postDto.getId());
//        session.merge(person);
//        session.merge(post);
        person.getLikes().remove(post);
        post.getPostLikers().remove(person);
        session.merge(person);
    }
}
