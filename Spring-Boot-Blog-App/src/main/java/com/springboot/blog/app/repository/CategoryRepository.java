package com.springboot.blog.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.app.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
