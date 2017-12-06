package com.bushemi.dao.impl;

import com.bushemi.converters.EntityDtoConverter;
import com.bushemi.dao.UserDao;
import com.bushemi.dao.entity.Person;
import com.bushemi.dao.entity.User;
import com.bushemi.exceptions.BadPasswordException;
import com.bushemi.model.entity.PersonDto;
import com.bushemi.model.entity.UserDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
@Transactional(readOnly = true)
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
	@Transactional
	public void delete(UserDto entity) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, entity.getId());
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
	public UserDto findByPerson(PersonDto person) {
		User user = (User) sessionFactory.getCurrentSession()
				.createQuery("select u from com.bushemi.dao.entity.User u where u.person.id = :person")
				.setParameter("person", person.getId())
				.uniqueResult();


		return EntityDtoConverter.convert(user);
	}

	@Override
	@Transactional
	public Long create(UserDto userDto) {
		User user = EntityDtoConverter.convert(userDto);

		Session session = sessionFactory.getCurrentSession();
		Person person = user.getPerson();
		session.merge(person);
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

	@Override
	public boolean isLoginFree(String login) {
		boolean result;
		User user = (User) sessionFactory.getCurrentSession()
				.createQuery("select u from com.bushemi.dao.entity.User u where u.login = :login")
				.setParameter("login", login)
				.uniqueResult();
		if (user == null) {result = true;}
		else {result = false;}
		return result;
	}

	@Override
	public UserDto findUserByLoginPassword(String login, String password) {
		User user = (User) sessionFactory.getCurrentSession()
				.createQuery("select u from com.bushemi.dao.entity.User u where u.login = :login and u.password = :password ")
				.setParameter("login", login)
				.setParameter("password", password)
				.uniqueResult();
		if (user == null) {throw new BadPasswordException("Bad login or password.");}
		return EntityDtoConverter.convert(user);
	}


}