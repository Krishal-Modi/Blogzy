package com.example.Blogzy.repository;

import com.example.Blogzy.entity.Replies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepliesRepository extends JpaRepository<Replies, String> {

    List<Replies> findByComment_FeedCommentsIdOrderByCreatedAtAsc(String feedCommentsId);

}
