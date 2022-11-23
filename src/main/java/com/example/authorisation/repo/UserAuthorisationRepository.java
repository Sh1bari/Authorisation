package com.example.authorisation.repo;

import com.example.authorisation.models.entity.UserAuthorisation;
import org.springframework.data.repository.CrudRepository;

public interface UserAuthorisationRepository extends CrudRepository<UserAuthorisation,Integer> {
    UserAuthorisation findByLogin(String login);
    boolean existsByLogin(String login);
}
