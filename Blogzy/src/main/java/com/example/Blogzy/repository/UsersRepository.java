package com.example.Blogzy.repository;

import com.example.Blogzy.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

//    User findByEmail(String authenticatedEmail);

    @Query(value = "SELECT DISTINCT u.* FROM users u " +
            "WHERE " +
            "LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(u.first_name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(u.last_name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(u.phone_number) LIKE LOWER(CONCAT('%', :search, '%'))",
            nativeQuery = true)
    List<Users> searchUsers(@Param("search") String search);


    Users findByEmail(String email);
}
