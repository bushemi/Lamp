package com.bushemi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by igor on 10.10.17.
 * useless comment
 */
@Controller("main")
public class MainController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getLogin(Model model) {

        return "MainPage";
    }
}
