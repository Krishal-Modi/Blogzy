package com.example.Blogzy.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FeedModel {

    private String content;
    private LocalDateTime createdAt;

}
