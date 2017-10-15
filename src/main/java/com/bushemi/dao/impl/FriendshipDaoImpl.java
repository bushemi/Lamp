package com.bushemi.dao.impl;

import com.bushemi.converters.EntityDtoConverter;
import com.bushemi.dao.FriendshipDao;
import com.bushemi.dao.entity.Friendship;
import com.bushemi.dao.entity.Person;
import com.bushemi.model.FriendshipDto;
import com.bushemi.model.PersonDto;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by igor on 01.09.17.
 * useless comment
 */
@Repository
@Transactional
public class FriendshipDaoImpl implements FriendshipDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public FriendshipDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(FriendshipDto entity) {
        Friendship friends = EntityDtoConverter.convert(entity);
        Session session = sessionFactory.getCurrentSession();

        friends.getPerson().getFriendships().add(friends);
        friends.getFriend().getFriendships().add(friends);
        session.save(friends);
    }



    @Override
    public void delete(FriendshipDto entity) {
        Friendship friends = EntityDtoConverter.convert(entity);
        Session session = sessionFactory.getCurrentSession();
        friends.getPerson().getFriendships().remove(friends);
        friends.getFriend().getFriendships().remove(friends);
        session.delete(friends);
    }

    @Override
    public void update(FriendshipDto entity) {
        Friendship friends = EntityDtoConverter.convert(entity);
        Session session = sessionFactory.getCurrentSession();
//        session.merge(friends.getPerson());
//        session.merge(friends.getFriend());
        session.update(friends);
    }

    @Override
    public Collection<FriendshipDto> findAll() {
        Session session = sessionFactory.getCurrentSession();
        		List<Friendship> items = session.createQuery("FROM " + Friendship.class.getSimpleName())
				.list();
                List<FriendshipDto> result = items.stream().map(EntityDtoConverter::convert).collect(toList());
		return items.stream().map(EntityDtoConverter::convert).collect(toList());
    }

    @Override
    public Collection<FriendshipDto> findFriendsOf(PersonDto person) {
        Session session = sessionFactory.getCurrentSession();
        Person person1 = EntityDtoConverter.convert(person);

        Criteria criteria = session.createCriteria(Friendship.class);
        Long id = person1.getId();
        criteria.add(Restrictions.or(Restrictions.eq("friends.person.id", id)
                                    ,Restrictions.eq("friends.friend.id", id)));

		List<Friendship> friends = criteria.list();

		return friends.stream().map(EntityDtoConverter::convert).collect(toList());
    }
}
