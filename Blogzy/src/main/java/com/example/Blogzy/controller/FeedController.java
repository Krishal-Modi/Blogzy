package com.example.Blogzy.controller;

import com.example.Blogzy.model.FeedModel;
import com.example.Blogzy.model.ParentFeedModel;
import com.example.Blogzy.service.FeedService;
import com.example.Blogzy.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {

    private final FeedService feedService;

    private final JwtUtil jwtUtil;

    @PostMapping("/createContent")
    public ResponseEntity<ParentFeedModel> createContent(@RequestHeader("Authorization") String tokenHeader,
                                                         @RequestBody FeedModel feedModel){
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return ResponseEntity.ok(feedService.createContent(authenticatedEmail, feedModel));
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<ParentFeedModel>> getAll(@RequestHeader("Authorization") String tokenHeader){
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return ResponseEntity.ok(feedService.allFeed());
    }
}
