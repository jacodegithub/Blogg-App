package com.springboot.blog.app.repository;

import com.springboot.blog.app.model.Category;
import com.springboot.blog.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.app.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCategory(Category category);

    List<Post> findByUser(User user);
}
