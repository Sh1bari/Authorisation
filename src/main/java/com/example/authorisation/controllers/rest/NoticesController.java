package com.example.authorisation.controllers.rest;

import com.example.authorisation.models.entity.UserAuthorisation;
import com.example.authorisation.models.entity.UserNotices;
import com.example.authorisation.repo.UserAuthorisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@RestController
public class NoticesController {
    @Autowired
    private UserAuthorisationRepository userAuthorisationRepository;


    @PostMapping ("/api/v1/addNotice")
    public void addUser(@RequestBody UserNotices user, HttpSession httpSession){
        if(httpSession.getAttribute("username")!=null) {
            user.setId(0);
            UserAuthorisation user1 = userAuthorisationRepository.findByLogin(httpSession.getAttribute("username").toString());
            user1.getNotices().add(user);
            userAuthorisationRepository.save(user1);
        }
    }

    @GetMapping("/api/v1/test")
    public Object test(HttpSession httpSession){
        return userAuthorisationRepository.findByLogin(httpSession.getAttribute("username").toString());
    }
    @GetMapping("/addNotice")
    public ModelAndView addUserShow(){
        return new ModelAndView("noticesAddModule/noticeAdd");
    }
}
