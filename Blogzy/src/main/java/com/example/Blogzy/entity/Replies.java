package com.example.Blogzy.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "replies")
@Data
public class Replies {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "reply_id", updatable = false, nullable = false)
    private String replyId;

    @Column(name = "reply_text")
    private String replyText;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Mapping
    @ManyToOne
    @JoinColumn(name = "feed_comment_id", nullable = false)
    private Comments comment;

    @ManyToOne
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private Users users;

    //PrePersist
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
