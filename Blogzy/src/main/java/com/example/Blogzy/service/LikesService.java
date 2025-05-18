package com.example.Blogzy.service;

import com.example.Blogzy.entity.Feed;
import com.example.Blogzy.entity.Likes;
import com.example.Blogzy.entity.Users;
import com.example.Blogzy.exceptions.DataNotFoundException;
import com.example.Blogzy.exceptions.DataValidationException;
import com.example.Blogzy.model.LikedFeedModel;
import com.example.Blogzy.model.LikesOnFeedModel;
import com.example.Blogzy.model.LikesResponseModel;
import com.example.Blogzy.repository.FeedRepository;
import com.example.Blogzy.repository.LikesRepository;
import com.example.Blogzy.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LikesService {

    private final UsersRepository usersRepository;

    private final FeedRepository feedRepository;

    private final LikesRepository likesRepository;


    @Transactional
    public LikesOnFeedModel likePost(String email, String feedId) {
        Users users = usersRepository.findByEmail(email);

        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new DataNotFoundException("Feed not found"));

        boolean alreadyLiked = likesRepository.existsByUsersUsersIdAndFeedFeedId(users.getUsersId(), feedId);
        if (alreadyLiked) {
            throw new DataValidationException("You already liked this post.");
        }

        Likes like = new Likes();
        like.setUsers(users);
        like.setFeed(feed);
        like.setLikes("like");

        likesRepository.save(like);

        LikesOnFeedModel model = new LikesOnFeedModel();
        model.setLikes("like");
        model.setContent(feed.getContent());
        model.setUsername(feed.getUsers().getUsername());

        return model;
    }


    public LikesResponseModel getTotalLikes(String feedId) {
        List<Likes> likesList = likesRepository.findByFeedFeedId(feedId);

        List<String> usernames = likesList.stream()
                .map(like -> like.getUsers().getUsername())
                .toList();

        return new LikesResponseModel(usernames.size(), usernames);
    }


    public List<LikedFeedModel> getLikedFeeds(String email) {
        Users users = usersRepository.findByEmail(email);

        List<Likes> likesList = likesRepository.findByUsersUsersId(users.getUsersId());

        List<LikedFeedModel> likedFeedModels = likesList.stream()
               .map(like -> {
                   LikedFeedModel model = new LikedFeedModel();
                   model.setUsername(like.getFeed().getUsers().getUsername());
                   model.setContent(Collections.singletonList(like.getFeed().getContent()));
                   return model;
               })
               .toList();
        return likedFeedModels;
    }
}
