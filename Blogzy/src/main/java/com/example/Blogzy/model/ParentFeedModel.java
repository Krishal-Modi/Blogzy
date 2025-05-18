package com.example.Blogzy.model;

import lombok.Data;

@Data
public class ParentFeedModel {

    private String firstName;
    private String lastName;
    private String username;
    private FeedModel feedModel;

}
