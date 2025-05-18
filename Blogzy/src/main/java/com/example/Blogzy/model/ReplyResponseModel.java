package com.example.Blogzy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReplyResponseModel {

    private String username;
    private String reply;
    private LocalDateTime createdAt;

}