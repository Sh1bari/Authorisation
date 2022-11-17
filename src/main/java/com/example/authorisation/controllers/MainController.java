package com.example.authorisation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(){
        return "mainPage/home";
//        return "authorisationModule/authorisation";
    }
    @GetMapping("/test")
    public String test(){
       return "authorisationModule/authorisation";
    }
}
