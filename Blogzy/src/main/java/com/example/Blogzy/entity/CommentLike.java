package com.example.Blogzy.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "comment_likes") // You can name this table as you prefer
@Data
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "comment_like_id", updatable = false, nullable = false)
    private String commentLikeId;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comments comment;

    @ManyToOne
    @JoinColumn(name = "reply_id")
    private Replies reply;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
}
