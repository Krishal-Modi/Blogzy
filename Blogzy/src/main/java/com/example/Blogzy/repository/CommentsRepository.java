package com.example.Blogzy.repository;

import com.example.Blogzy.entity.Comments;
import com.example.Blogzy.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, String> {

    List<Comments> findByFeedFeedId(String feedById);
}
