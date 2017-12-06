package com.bushemi.service.impl;

import com.bushemi.converters.PasswordEncoder;
import com.bushemi.dao.UserDao;
import com.bushemi.model.entity.PersonDto;
import com.bushemi.model.entity.UserDto;
import com.bushemi.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by igor on 08.10.17.
 * useless comment
 */
@Service
@Transactional
public class AuthorizationServiceImpl implements AuthorizationService {
    private PasswordEncoder encoder;
    private UserDao userDao;

    @Autowired

    public AuthorizationServiceImpl(PasswordEncoder encoder, UserDao userDao) {
        this.encoder = encoder;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public PersonDto authorization(String login, String password) {
        UserDto userByLoginPassword = userDao.findUserByLoginPassword(login, encoder.encodePassword(password));
        return userByLoginPassword.getPerson();
    }
}
