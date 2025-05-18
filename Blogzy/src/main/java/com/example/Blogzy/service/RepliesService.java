package com.example.Blogzy.service;

import com.example.Blogzy.entity.Comments;
import com.example.Blogzy.entity.Feed;
import com.example.Blogzy.entity.Replies;
import com.example.Blogzy.entity.Users;
import com.example.Blogzy.exceptions.DataNotFoundException;
import com.example.Blogzy.mapper.RepliesMapper;
import com.example.Blogzy.model.ReplyRequestModel;
import com.example.Blogzy.model.ReplyResponseModel;
import com.example.Blogzy.repository.CommentsRepository;
import com.example.Blogzy.repository.RepliesRepository;
import com.example.Blogzy.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepliesService {

    private final RepliesRepository repliesRepository;

    private final CommentsRepository commentsRepository;

    private final UsersRepository usersRepository;

    public List<ReplyResponseModel> repliedToPost(String feedCommentsId, String email, ReplyRequestModel reply) {

        Comments comments = commentsRepository.findById(feedCommentsId)
                .orElseThrow(() -> new DataNotFoundException("There is no such Id To comment"));

        Users userId = usersRepository.findByEmail(email);
        Users users = usersRepository.findById(userId.getUsersId())
                .orElseThrow(() -> new DataNotFoundException("User Not found"));

        Feed feed = comments.getFeed();

        Replies replies = new Replies();
        replies.setReplyText(reply.getReply());
        replies.setUsers(users);
        replies.setFeed(feed);
        replies.setComment(comments);
        repliesRepository.save(replies);

        // For Output
        List<Replies> allReplies = repliesRepository.findByComment_FeedCommentsIdOrderByCreatedAtAsc(feedCommentsId);
        // Here it will Go to Comment entity from Replies entity -> then find feed_comment_id -> then order by


        return allReplies.stream()
                .map(r -> new ReplyResponseModel(
                        r.getUsers().getUsername(),
                        r.getReplyText(),
                        r.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }


    public ResponseEntity<String> deleteReply(String replyId) {

        Replies replies = repliesRepository.findById(replyId)
               .orElseThrow(() -> new DataNotFoundException("Reply Not found"));

        repliesRepository.delete(replies);

        return ResponseEntity.ok("Reply deleted successfully");

    }
}
