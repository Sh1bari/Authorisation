package com.example.authorisation.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UserAuthorisation {

    @Id
    private String login;

    private String password;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "login")
    private List<UserNotices> notices = new ArrayList<>();


}
