package com.bushemi.service;

import com.bushemi.model.entity.PersonDto;

/**
 * Created by igor on 08.10.17.
 * useless comment
 */
public interface AuthorizationService {
    PersonDto authorization(String login, String password);
}
