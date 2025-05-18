package com.example.Blogzy.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "feed_likes")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "feed_like_id", updatable = false, nullable = false)
    private String feedLikeId;

    @Column(name = "likes")
    private String likes;

    // Mapping
    @ManyToOne
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;


}
