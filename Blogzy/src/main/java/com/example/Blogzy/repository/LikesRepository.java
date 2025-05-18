package com.example.Blogzy.repository;

import com.example.Blogzy.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, String> {

    boolean existsByUsersUsersIdAndFeedFeedId(String usersId, String feedId);

    List<Likes> findByFeedFeedId(String feedId);

    List<Likes> findByUsersUsersId(String usersId);
}
