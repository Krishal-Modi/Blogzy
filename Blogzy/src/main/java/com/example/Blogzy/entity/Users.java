package com.example.Blogzy.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "users_id", updatable = false, nullable = false)
    private String usersId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "bio")
    private String bio;


    // Mapping
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Feed> feeds;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Likes> likes;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Comments> comments;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Replies> replies;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CommentLike> commentLikes;
}
