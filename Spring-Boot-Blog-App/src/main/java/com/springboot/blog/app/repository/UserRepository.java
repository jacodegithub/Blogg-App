package com.springboot.blog.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.app.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Optional<User> findByUsernameOrEmailId(String username, String email);
}
