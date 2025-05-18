package com.example.Blogzy.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "feed_comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "feed_comments_id", updatable = false, nullable = false)
    private String feedCommentsId;

    @Column(name = "comments")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<Replies> replies;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<CommentLike> commentLikes;

}
