package com.bushemi.service;

import com.bushemi.model.entity.UserDto;

/**
 * Created by igor on 08.10.17.
 * useless comment
 */
public interface RegistrationService {
    UserDto registrationNewUser(String login, String password, String email);

}
