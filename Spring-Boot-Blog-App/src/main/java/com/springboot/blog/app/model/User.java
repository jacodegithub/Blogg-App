package com.springboot.blog.app.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseModel {
    
    private String userName;
    
    private String emailId;

    private String password;

    // private List<Post> posts;
}
