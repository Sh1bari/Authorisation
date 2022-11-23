package com.example.authorisation.controllers;

import com.example.authorisation.models.entity.UserAuthorisation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(){
        return "mainPage/home";
    }
}
