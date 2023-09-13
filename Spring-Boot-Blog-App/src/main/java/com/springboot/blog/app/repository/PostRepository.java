package com.springboot.blog.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.app.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    
}
