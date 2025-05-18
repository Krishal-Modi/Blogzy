package com.example.Blogzy.model;

import lombok.Data;

import java.util.List;

@Data
public class LikedFeedModel {

    private String username;
    private List<String> content;

}
