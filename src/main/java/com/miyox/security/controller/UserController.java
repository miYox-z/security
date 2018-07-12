package com.miyox.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String userIndex(){
        return "user/index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String userIndex1(){
        return "user/index";
    }
}
