package com.example.Blogzy.service;

import com.example.Blogzy.entity.CommentLike;
import com.example.Blogzy.entity.Comments;
import com.example.Blogzy.entity.Replies;
import com.example.Blogzy.entity.Users;
import com.example.Blogzy.exceptions.DataNotFoundException;
import com.example.Blogzy.exceptions.DataValidationException;
import com.example.Blogzy.model.CommentLikeResponseModel;
import com.example.Blogzy.repository.CommentLikeRepository;
import com.example.Blogzy.repository.CommentsRepository;
import com.example.Blogzy.repository.RepliesRepository;
import com.example.Blogzy.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final UsersRepository usersRepository;
    private final CommentsRepository commentsRepository;
    private final RepliesRepository repliesRepository;
    private final CommentLikeRepository commentLikeRepository;

    public CommentLikeResponseModel handleLike(String email, String feedCommentId, String replyId) {
        Users userId = usersRepository.findByEmail(email);

        Users user = usersRepository.findById(userId.getUsersId())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        if (feedCommentId != null) {
            Comments comment = commentsRepository.findById(feedCommentId)
                    .orElseThrow(() -> new DataNotFoundException("Comment not found"));

            boolean alreadyLiked = commentLikeRepository.existsByUserUsersIdAndCommentFeedCommentsId(user.getUsersId(), feedCommentId);
            if (alreadyLiked) {
                return new CommentLikeResponseModel("You already liked this comment",
                        commentLikeRepository.countByComment(comment));
            }

            CommentLike like = new CommentLike();
            like.setUser(user);
            like.setComment(comment);
            commentLikeRepository.save(like);

            return new CommentLikeResponseModel("Liked comment successfully",
                    commentLikeRepository.countByComment(comment));
        }

        if (replyId != null) {
            Replies reply = repliesRepository.findById(replyId)
                    .orElseThrow(() -> new DataNotFoundException("Reply not found"));

            boolean alreadyLiked = commentLikeRepository.existsByUserUsersIdAndReplyReplyId(user.getUsersId(), replyId);
            if (alreadyLiked) {
                return new CommentLikeResponseModel("You already liked this reply",
                        commentLikeRepository.countByReply(reply));
            }

            CommentLike like = new CommentLike();
            like.setUser(user);
            like.setReply(reply);
            commentLikeRepository.save(like);

            return new CommentLikeResponseModel("Liked reply successfully",
                    commentLikeRepository.countByReply(reply));
        }

        throw new DataValidationException("Either feedCommentId or replyId must be provided!!");
    }
}
