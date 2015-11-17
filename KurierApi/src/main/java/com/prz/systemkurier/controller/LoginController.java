package com.prz.systemkurier.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;


@RestController
@RequestMapping("/test")
public class LoginController {

    @PostConstruct
    public void test(){
        System.out.println("login controller");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(){
        return "start";
    }
}
