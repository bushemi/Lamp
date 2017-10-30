package com.bushemi.controller;

import com.bushemi.exceptions.UserRegisteredException;
import com.bushemi.filter.LoginFilter;
import com.bushemi.model.AuthData;
import com.bushemi.model.PersonInfo;
import com.bushemi.model.ResponseMessage;
import com.bushemi.model.entity.PersonDto;
import com.bushemi.model.entity.UserDto;
import com.bushemi.service.PersonService;
import com.bushemi.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * Created by igor on 10.10.17.
 * useless comment
 */
@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getLogin(Model model) {

        return "RegistrationPage";
    }

    @ResponseBody
    @RequestMapping(value = "/submit-reg", method = RequestMethod.POST)
    public ResponseMessage regUser(@RequestBody AuthData authData, HttpServletRequest request) {

        if (authData.getLogin() == null || authData.getPassword() == null || authData.getEmail() == null ||
        authData.getLogin().trim().length() < 1 || authData.getPassword().trim().length() < 1 || authData.getEmail().trim().length() < 1 ){
            return ResponseMessage.errorMessage("bad login/password/email");
        }
        UserDto user;
        try {
            user = registrationService.registrationNewUser(authData.getLogin(), authData.getPassword(), authData.getEmail());
        }catch(UserRegisteredException ure){
            return ResponseMessage.errorMessage(ure.getMessage());
        }
        request.getSession().setAttribute(LoginFilter.LOGIN_ATTR, LocalDateTime.now());
        request.getSession().setAttribute("id", user.getPerson().getId());

        return ResponseMessage.okMessage(null);
    }

    @ResponseBody
    @RequestMapping(value = "/submit-info", method = RequestMethod.POST)
    public ResponseMessage regPersonInfo(@RequestBody PersonInfo personInfo, HttpServletRequest request) {
        String id = String.valueOf(request.getSession().getAttribute("id"));
        PersonDto p = null;
        try {
            p = personService.createPerson(
                    Long.parseLong(id),
                    personInfo.getFirstName(),
                    personInfo.getLastName(),
                    personInfo.getNickname(),
                    personInfo.getBirthday(),
                    personInfo.getPhotoURL());
        }catch(UserRegisteredException ure){
            return ResponseMessage.errorMessage(ure.getMessage());
        }
        request.getSession().setAttribute(LoginFilter.LOGIN_ATTR, LocalDateTime.now());
        request.getSession().setAttribute("id", p.getId());

        return ResponseMessage.okMessage(personInfo);
    }
}
