package com.example.authorisation.controllers.rest;
import com.example.authorisation.models.AuthorisationAnswer;
import com.example.authorisation.models.UserAuthorisation;
import com.example.authorisation.repo.UserAuthorisationRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class AuthorisationController {
    private final UserAuthorisationRepository userAuthorisationRepository;

    public AuthorisationController(UserAuthorisationRepository userAuthorisationRepository) {
        this.userAuthorisationRepository = userAuthorisationRepository;
    }

    @PostMapping( "/authorisation/user/add")
    public Object authorisationAdd(@RequestBody UserAuthorisation user){
        var authorisationAnswer = new AuthorisationAnswer();
        String login = user.getLogin();
        if(!userAuthorisationRepository.existsByLogin(login)){
            user.setId(0);
            userAuthorisationRepository.save(user);
            authorisationAnswer.setLoginAnswer("success");
        }else{
            authorisationAnswer.setLoginAnswer("denied");
        }
        return authorisationAnswer;
    }
    @PostMapping("/authorisation/user/login")
    public Object authorisationLogin(@RequestBody UserAuthorisation user){
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
            }
        }
        return authorisationAnswer;
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
