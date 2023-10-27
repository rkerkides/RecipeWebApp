package com.rkerkides.recipewebapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")  // Specify a different table name to avoid reserved keyword conflicts
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;
    private String username;
    private String email;
    private String password;
    private Date dateJoined;

    @OneToMany(mappedBy = "user")
    private Set<Recipe> recipes;

    @OneToMany(mappedBy = "user")
    private Set<Post> posts;
}
