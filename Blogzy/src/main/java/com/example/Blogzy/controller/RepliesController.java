package com.example.Blogzy.controller;

import com.example.Blogzy.model.ReplyRequestModel;
import com.example.Blogzy.model.ReplyResponseModel;
import com.example.Blogzy.service.RepliesService;
import com.example.Blogzy.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies")
public class RepliesController {

    private final RepliesService repliesService;

    private final JwtUtil jwtUtil;

    @PostMapping("/replyToComment")
    public ResponseEntity<List<ReplyResponseModel>> repliedToPost(@RequestParam String feedCommentsId,
                                                                  @RequestHeader("Authorization") String tokenHeader,
                                                                  @RequestBody ReplyRequestModel reply){
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return ResponseEntity.ok(repliesService.repliedToPost(feedCommentsId, authenticatedEmail, reply));
    }


    @DeleteMapping("/deleteReply")
    public ResponseEntity<String> deleteReply(@RequestHeader("Authorization") String tokenHeader,
                                              @RequestParam String replyId){
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return repliesService.deleteReply(replyId);
    }

}
