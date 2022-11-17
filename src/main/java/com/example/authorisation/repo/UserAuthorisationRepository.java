package com.example.authorisation.repo;

import com.example.authorisation.models.UserAuthorisation;
import org.springframework.data.repository.CrudRepository;

public interface UserAuthorisationRepository extends CrudRepository<UserAuthorisation,Integer> {
}
