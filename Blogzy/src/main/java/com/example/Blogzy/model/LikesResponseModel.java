package com.example.Blogzy.model;

import lombok.Data;

import java.util.List;

@Data
public class LikesResponseModel {

    private int likes;
    private List<String> username;

    public LikesResponseModel(int likes, List<String> username) {
        this.likes = likes;
        this.username = username;
    }

}
