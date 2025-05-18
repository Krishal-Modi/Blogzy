package com.example.Blogzy.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsersModel {

    private String firstName;
    private String lastName;
    private String username;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private String password;
    private String gender;
    private String bio;

}
