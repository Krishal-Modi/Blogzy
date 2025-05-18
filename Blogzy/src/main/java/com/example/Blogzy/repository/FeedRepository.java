package com.example.Blogzy.repository;

import com.example.Blogzy.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, String> {

    List<Feed> findAllByOrderByCreatedAtDesc();
}
