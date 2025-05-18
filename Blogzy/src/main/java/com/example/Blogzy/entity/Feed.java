package com.example.Blogzy.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "feeds")
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "feed_id", updatable = false, nullable = false)
    private String feedId;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;


    // Mapping
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
    private List<Likes> Likes;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
    private List<Comments> comments;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
    private List<Replies> replies;


    // PrePersist
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
