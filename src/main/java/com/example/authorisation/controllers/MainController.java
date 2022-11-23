package com.example.authorisation.controllers;

import com.example.authorisation.models.entity.UserAuthorisation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(){
        return "mainPage/home";
    }

    @GetMapping("/api/v1/addNotice")
    public String addUserShow(){
        return "noticesAddModule/noticeAdd";
    }
}
