package com.example.authorisation.controllers.rest;
import com.example.authorisation.models.response.AuthorisationAnswer;
import com.example.authorisation.models.entity.UserAuthorisation;
import com.example.authorisation.repo.UserAuthorisationRepository;
import com.example.authorisation.services.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@RestController
public class AuthorisationController {
    @Autowired
    private UserAuthorisationRepository userAuthorisationRepository;

    @Autowired
    private ServiceImpl service;

    @PostMapping( "/authorisation/user/add")
    public Object authorisationAdd(@RequestBody UserAuthorisation user){
        var authorisationAnswer = new AuthorisationAnswer();

        if (service.saveUser(user)){
            authorisationAnswer.setLoginAnswer("success");
        }else{
            authorisationAnswer.setLoginAnswer("denied");
        }
        return authorisationAnswer;
    }
    @PostMapping("/authorisation/user/login")
    public Object authorisationLogin(@RequestBody UserAuthorisation user, HttpSession httpSession){
        var authorisationAnswer = new AuthorisationAnswer();
        String login = user.getLogin();
        String password = user.getPassword();
        if (!userAuthorisationRepository.existsByLogin(login)){
            authorisationAnswer.setLoginAnswer("incorrect login");
        }else {
            authorisationAnswer.setLoginAnswer("correct login");
            UserAuthorisation userAuthorisation = userAuthorisationRepository.findByLogin(login);
            if(!userAuthorisation.getPassword().equals(password)){
                authorisationAnswer.setPasswordAnswer("incorrect password");
            }else{
                authorisationAnswer.setPasswordAnswer("correct password");
                httpSession.setAttribute("username", user.getLogin());
            }
        }
        return authorisationAnswer;
    }
    @GetMapping("/authorisation/user/logout")
    public void logout(HttpSession httpSession){
        httpSession.setAttribute("username", null);
    }

    @RequestMapping (method = RequestMethod.GET, value = "/authorisationFormLogin")
    public ModelAndView showFormLogin(){
        return new ModelAndView("authorisationModule/authorisationLogin");
    }
    @RequestMapping (method = RequestMethod.GET, value = "/authorisationFormSignup")
    public ModelAndView showFormSignup(){
        return new ModelAndView("authorisationModule/authorisationSignup");
    }

}
