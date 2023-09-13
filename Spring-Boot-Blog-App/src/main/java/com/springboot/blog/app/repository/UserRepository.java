package com.springboot.blog.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
