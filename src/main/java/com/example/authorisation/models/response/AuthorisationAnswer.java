package com.example.authorisation.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AuthorisationAnswer {

    private String loginAnswer;

    private  String passwordAnswer;
}
