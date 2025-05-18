package com.example.Blogzy.controller;

import com.example.Blogzy.model.LikedFeedModel;
import com.example.Blogzy.model.LikesOnFeedModel;
import com.example.Blogzy.model.LikesResponseModel;
import com.example.Blogzy.service.LikesService;
import com.example.Blogzy.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedLikes")
public class LikesController {

    private final LikesService likesService;

    private final JwtUtil jwtUtil;

    @PostMapping("/likeAPost")
    public ResponseEntity<LikesOnFeedModel> likes(@RequestHeader("Authorization") String tokenHeader,
                                                  @RequestParam String feedId){
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return ResponseEntity.ok(likesService.likePost(authenticatedEmail, feedId));
    }


    @GetMapping("/totalLikes")
    public ResponseEntity<LikesResponseModel> totalLikes(@RequestHeader("Authorization") String tokenHeader,
                                                         @RequestParam String feedId) {
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return ResponseEntity.ok(likesService.getTotalLikes(feedId));
    }

    @GetMapping("/getLikedFeeds")
    public ResponseEntity<List<LikedFeedModel>> getLikedFeeds(@RequestHeader("Authorization") String tokenHeader) {
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return ResponseEntity.ok(likesService.getLikedFeeds(authenticatedEmail));
    }
}
