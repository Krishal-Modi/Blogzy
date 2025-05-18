package com.example.Blogzy.model;

import lombok.Data;

@Data
public class CommentLikeRequestModel {

    private String userId;
    private String feedCommentId;
    private String replyId;

}
