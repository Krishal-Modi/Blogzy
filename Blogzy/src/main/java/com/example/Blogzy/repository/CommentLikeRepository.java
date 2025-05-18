package com.example.Blogzy.repository;

import com.example.Blogzy.entity.CommentLike;
import com.example.Blogzy.entity.Comments;
import com.example.Blogzy.entity.Replies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, String> {


    int countByComment(Comments comment);

    int countByReply(Replies reply);

    boolean existsByUserUsersIdAndCommentFeedCommentsId(String userId, String commentId);

    boolean existsByUserUsersIdAndReplyReplyId(String userId, String replyId);


}
