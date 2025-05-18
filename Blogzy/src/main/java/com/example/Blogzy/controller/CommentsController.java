package com.example.Blogzy.controller;

import com.example.Blogzy.model.CommentsFeedModel;
import com.example.Blogzy.model.CommentsRequestModel;
import com.example.Blogzy.service.CommentsService;
import com.example.Blogzy.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedComments")
public class CommentsController {

    private final CommentsService commentsService;

    private final JwtUtil jwtUtil;

    @PostMapping("/commentOnPost")
    public ResponseEntity<CommentsFeedModel> postAComment(@RequestHeader("Authorization") String tokenHeader,
                                                          @RequestParam String feedId,
                                                          @RequestBody CommentsRequestModel comment){
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return commentsService.postAComment(authenticatedEmail, feedId, comment);
    }


    @GetMapping("/getComments")
    public ResponseEntity<List<CommentsFeedModel>> getComments(@RequestHeader("Authorization") String tokenHeader,
                                                               @RequestParam String feedId){
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return commentsService.getComments(feedId);
    }


    @DeleteMapping("/deleteComment")
    public ResponseEntity<String> deleteComment(@RequestHeader("Authorization") String tokenHeader,
                                                @RequestParam String commentId){
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return commentsService.deleteComment(commentId);
    }
}
