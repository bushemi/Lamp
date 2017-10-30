package com.bushemi.dao;



import com.bushemi.model.entity.PersonDto;
import com.bushemi.model.entity.UserDto;

import java.util.List;


public interface UserDao extends CrudOperationsInterface<UserDto>{

	List<UserDto> findAll();
	UserDto findById(long id);
	UserDto findByLogin(String login);
	UserDto findByPerson(PersonDto login);
	Long create(UserDto userDto);
	UserDto update(UserDto userDto);

    boolean isLoginFree(String login);
    UserDto findUserByLoginPassword(String login, String password);
}