package com.example.Blogzy.controller;

import com.example.Blogzy.model.CommentLikeResponseModel;
import com.example.Blogzy.service.CommentLikeService;
import com.example.Blogzy.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likeComment")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    private final JwtUtil jwtUtil;

    @PostMapping("/likeAMessage")
    public ResponseEntity<CommentLikeResponseModel> likeItem(@RequestHeader("Authorization") String tokenHeader,
                                                             @RequestParam(required = false) String feedCommentId,
                                                             @RequestParam(required = false) String replyId){
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return ResponseEntity.ok(commentLikeService.handleLike(authenticatedEmail, feedCommentId, replyId));
    }

}
