package com.springboot.blog.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post extends BaseModel {
    
    private String name;
    private String description;

    // private User user;
}
