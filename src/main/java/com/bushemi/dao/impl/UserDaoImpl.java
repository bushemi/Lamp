package com.bushemi.dao.impl;

import com.bushemi.converters.EntityDtoConverter;
import com.bushemi.dao.UserDao;
import com.bushemi.dao.entity.User;
import com.bushemi.model.PersonDto;
import com.bushemi.model.UserDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	private final SessionFactory sessionFactory;

	@Autowired
	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public List<UserDto> findAll() {
		List<User> users = sessionFactory.getCurrentSession()
				.createQuery("from com.bushemi.dao.entity.User")
				.list();
		return users.stream().map(EntityDtoConverter::convert).collect(toList());

	}
	@Override
	public UserDto findById(long id) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, id);
		return EntityDtoConverter.convert(user);
	}

	@Override
	public void delete(UserDto entity) {
		Session session = sessionFactory.getCurrentSession();
		User user = EntityDtoConverter.convert(entity);
		session.delete(user);

	}
	@Override
	public UserDto findByLogin(String login) {
		User user = (User) sessionFactory.getCurrentSession()
				.createQuery("select u from com.bushemi.dao.entity.User u where u.login = :login")
				.setParameter("login", login)
				.uniqueResult();


		return EntityDtoConverter.convert(user);
	}

	@Override
	public UserDto findByPerson(PersonDto login) {
		User user = (User) sessionFactory.getCurrentSession()
				.createQuery("select u from com.bushemi.dao.entity.User u where u.person.id = :person")
				.setParameter("person", login.getId())
				.uniqueResult();


		return EntityDtoConverter.convert(user);
	}

	@Override
	public Long create(UserDto userDto) {
		User user = EntityDtoConverter.convert(userDto);

		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		return user.getId();
	}

	@Override
	public UserDto update(UserDto userDto) {

		Session session = sessionFactory.getCurrentSession();
		User user = EntityDtoConverter.convert(userDto);
		session.merge(user);
		return EntityDtoConverter.convert(user);
	}


}