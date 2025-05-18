package com.example.Blogzy.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentLikeResponseModel {

    private String message;
    private int totalLikes;

}
