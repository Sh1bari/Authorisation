package com.example.authorisation.controllers.rest;

import com.example.authorisation.models.UserAuthorisation;
import com.example.authorisation.repo.UserAuthorisationRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AuthorisationController {
    private final UserAuthorisationRepository userAuthorisationRepository;

    public AuthorisationController(UserAuthorisationRepository userAuthorisationRepository) {
        this.userAuthorisationRepository = userAuthorisationRepository;
    }

    @PostMapping("/authorisation")
    public void authorisation(@RequestParam String login,
                              @RequestParam String password){
        UserAuthorisation userAuthorisation = new UserAuthorisation(0, login, password);
        userAuthorisationRepository.save(userAuthorisation);
    }

    @RequestMapping (method = RequestMethod.GET, value = "/authorisationForm")
    public ModelAndView showForm(){
        return new ModelAndView("authorisationModule/authorisation");
    }

}
