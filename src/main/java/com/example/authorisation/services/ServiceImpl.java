package com.example.authorisation.services;

import com.example.authorisation.models.entity.UserAuthorisation;
import com.example.authorisation.repo.UserAuthorisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl {
    @Autowired
    private UserAuthorisationRepository userAuthorisationRepository;

    public boolean saveUser(UserAuthorisation user){
        String login = user.getLogin();
        if(!userAuthorisationRepository.existsByLogin(login)) {
            userAuthorisationRepository.save(user);
            return true;
        } else return false;
    }

}

//    @PostMapping( "/authorisation/user/add")
//    public Object authorisationAdd(@RequestBody UserAuthorisation user){
//        var authorisationAnswer = new AuthorisationAnswer();
//        String login = user.getLogin();
//        if(!userAuthorisationRepository.existsByLogin(login)){
//            user.setId(0);
//            userAuthorisationRepository.save(user);
//            authorisationAnswer.setLoginAnswer("success");
//        }else{
//            authorisationAnswer.setLoginAnswer("denied");
//        }
//        return authorisationAnswer;
//    }
