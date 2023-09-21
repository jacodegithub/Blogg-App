package com.springboot.blog.app.model;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserInterface extends UserDetails {
   Long getUserId();

   String getEmailId();

   List<Post> getPost();

   Set<Role> getRole();
}
