package com.springboot.blog.app.service;

import com.springboot.blog.app.dto.UserDto;
import com.springboot.blog.app.model.User;

public interface UserService {

    UserDto creteUser(UserDto user);
}
