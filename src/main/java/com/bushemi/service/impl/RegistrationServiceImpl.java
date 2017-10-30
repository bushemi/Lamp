package com.bushemi.service.impl;

import com.bushemi.converters.PasswordEncoder;
import com.bushemi.dao.PersonDao;
import com.bushemi.dao.UserDao;
import com.bushemi.exceptions.UserRegisteredException;
import com.bushemi.model.entity.PersonDto;
import com.bushemi.model.entity.UserDto;
import com.bushemi.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by igor on 08.10.17.
 * useless comment
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserDao userDao;
    @Autowired
    PersonDao personDao;

    Random random = new Random();

    @Override
    public UserDto registrationNewUser(String login, String password, String email) throws UserRegisteredException{
        if (!userDao.isLoginFree(login)){
            throw new UserRegisteredException("That login isn't free.");
        }
        UserDto user = new UserDto();
        user.setLogin(login);
        user.setPassword(encoder.encodePassword(password));
        user.setEmail(email);
        PersonDto person = new PersonDto();
        person.setNickname(getTemporaryUniqueNickname(login));
        Long personId = personDao.create(person);
        person.setId(personId);
        user.setPerson(person);
        Long id = userDao.create(user);
        user.setId(id);
        return user;
    }

    private String getTemporaryUniqueNickname(String login) {
        return login+random.nextInt();
    }
}
