package com.example.authorisation.controllers.rest;
import com.example.authorisation.models.Exceptions;
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

    @PostMapping( "/authorisation/user/add")
    public void authorisationAdd(@RequestBody UserAuthorisation user){
        user.setId(0);
        userAuthorisationRepository.save(user);
    }
    @PostMapping("/authorisation/user/login")
    public Object authorisationLogin(@RequestBody UserAuthorisation user){
        String login = user.getLogin();
        Iterable<UserAuthorisation> userAuthorisation = userAuthorisationRepository.findByLogin(login);
        if (!userAuthorisationRepository.existsByLogin(login)){
            var exc = new Exceptions();
            exc.setExceptionName("Неверный логин");
            return exc;
        }else {
            System.out.println(userAuthorisation.toString());
            return userAuthorisation;
        }
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
