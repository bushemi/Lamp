package com.bushemi.dao.impl;

import com.bushemi.converters.EntityDtoConverter;
import com.bushemi.dao.FriendshipDao;
import com.bushemi.dao.entity.Friendship;
import com.bushemi.model.entity.FriendshipDto;
import com.bushemi.model.entity.PersonDto;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by igor on 01.09.17.
 * @Version 1.1
 * 1.1 - was added isFriends();
 */
@Repository
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
        Session session = sessionFactory.getCurrentSession();
        Friendship friendshipFirst = findFriendshipBetweenTwoPersons(entity.getPerson(), entity.getFriend());
        if(friendshipFirst == null){friendshipFirst = findFriendshipBetweenTwoPersons(entity.getFriend(), entity.getPerson());}
        session.delete(friendshipFirst);
    }

    @Override
    public void update(FriendshipDto entity) {
        Friendship friends = EntityDtoConverter.convert(entity);
        Session session = sessionFactory.getCurrentSession();
        session.merge(friends);
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
    public Collection<FriendshipDto> findFriendshipsOf(PersonDto person) {
        Long id = person.getId();
		return findFriendshipsByPersonId(id);
    }

    @Override
    public Collection<FriendshipDto> findFriendshipsByPersonId(long id) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Friendship.class);

        criteria.add(Restrictions.or(Restrictions.eq("friends.person.id", id)
                ,Restrictions.eq("friends.friend.id", id)));

        List<Friendship> friends = criteria.list();

        return friends.stream().map(EntityDtoConverter::convert).collect(toList());
    }

    @Override
    public boolean isFriends(PersonDto person, PersonDto friend) {
        Session session = sessionFactory.getCurrentSession();
        Long personId = person.getId();
        Long friendId = friend.getId();
        Criteria criteria = session.createCriteria(Friendship.class);

        criteria.add(Restrictions.or(Restrictions.and(Restrictions.eq("friends.person.id", personId),
                        Restrictions.eq("friends.friend.id", friendId)),
                Restrictions.and(Restrictions.eq("friends.person.id", friendId),
                        Restrictions.eq("friends.friend.id", personId))));
        List<Friendship> friends = criteria.list();

        return (!friends.isEmpty());
    }

    private Friendship findFriendshipBetweenTwoPersons(PersonDto person, PersonDto friend){
        Session session = sessionFactory.getCurrentSession();
        Long personId = person.getId();
        Long friendId = friend.getId();
        Criteria criteria = session.createCriteria(Friendship.class);

        criteria.add(Restrictions.and(Restrictions.eq("friends.person.id", personId),
                Restrictions.eq("friends.friend.id", friendId)));
        Friendship friendship = (Friendship) criteria.uniqueResult();
        return friendship;
    }
}
