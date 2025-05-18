package com.example.Blogzy.service;

import com.example.Blogzy.entity.Feed;
import com.example.Blogzy.entity.Users;
import com.example.Blogzy.exceptions.DataNotFoundException;
import com.example.Blogzy.exceptions.DataValidationException;
import com.example.Blogzy.mapper.FeedMapper;
import com.example.Blogzy.model.FeedModel;
import com.example.Blogzy.model.ParentFeedModel;
import com.example.Blogzy.repository.FeedRepository;
import com.example.Blogzy.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final UsersRepository usersRepository;

    private final FeedRepository feedRepository;

    private final FeedMapper feedMapper;

    public ParentFeedModel createContent(String email, FeedModel feedModel) {

        // Find the user by email
        Users users = usersRepository.findByEmail(email);

        // Create a new Feed entity and set the content
        Feed feed = new Feed();
        feed.setContent(feedModel.getContent());
        feed.setUsers(users);

        Feed savedFeed = feedRepository.save(feed);

        FeedModel savedFeedModel = feedMapper.feedToFeedModel(savedFeed);

        savedFeedModel.setCreatedAt(savedFeed.getCreatedAt());

        // Prepare the response
        ParentFeedModel response = new ParentFeedModel();
        response.setFirstName(users.getFirstName());
        response.setLastName(users.getLastName());
        response.setUsername(users.getUsername());
        response.setFeedModel(savedFeedModel);

        return response;
    }

    public List<ParentFeedModel> allFeed(){
        List<Feed> posts = feedRepository.findAllByOrderByCreatedAtDesc();

        return posts.stream()
                .map(post -> {
                    ParentFeedModel model = new ParentFeedModel();
                    model.setFirstName(post.getUsers().getFirstName());
                    model.setLastName(post.getUsers().getLastName());
                    model.setUsername(post.getUsers().getUsername());

                    FeedModel feedModel = feedMapper.feedToFeedModel(post);
                    feedModel.setCreatedAt(post.getCreatedAt());
                    model.setFeedModel(feedModel);

                    return model;
                }).toList();
    }

}