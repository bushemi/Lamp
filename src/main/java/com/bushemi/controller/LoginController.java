package com.bushemi.controller;

import com.bushemi.exceptions.BadPasswordException;
import com.bushemi.filter.LoginFilter;
import com.bushemi.model.AuthData;
import com.bushemi.model.PersonInfo;
import com.bushemi.model.ResponseMessage;
import com.bushemi.model.entity.PersonDto;
import com.bushemi.service.AuthorizationService;
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
public class LoginController {

    @Autowired
    private AuthorizationService authorizationService;



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(Model model) {

        return "LoginPage";
    }


    @ResponseBody
    @RequestMapping(value = "/submitLogin", method = RequestMethod.POST)
    public ResponseMessage submitLogin(@RequestBody AuthData authData, HttpServletRequest request) {

        PersonDto person = null;
        try {
           person = authorizationService.authorization(authData.getLogin(), authData.getPassword());
        }catch(BadPasswordException bpe){
           return ResponseMessage.errorMessage(bpe.getMessage());
        }
        request.getSession().setAttribute(LoginFilter.LOGIN_ATTR, LocalDateTime.now());
        request.getSession().setAttribute("id", person.getId());
        PersonInfo p = new PersonInfo(person);
        System.out.println("Log in successfully:" + p);
        return ResponseMessage.okMessage(p);
    }
}
