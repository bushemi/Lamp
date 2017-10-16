package com.bushemi.converters;

import org.springframework.stereotype.Component;

/**
 * Created by igor on 08.10.17.
 * useless comment
 */
@Component
public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public String encodePassword(String password) {
        String newPassword = new String(password);

        return String.valueOf(newPassword.hashCode());
    }
}
