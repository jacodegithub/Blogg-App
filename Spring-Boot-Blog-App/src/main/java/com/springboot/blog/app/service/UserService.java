package com.springboot.blog.app.service;

import com.springboot.blog.app.dto.UserDto;
import com.springboot.blog.app.model.User;

import java.util.List;

public interface UserService {

    UserDto creteUser(UserDto user);

    List<UserDto> getUsers();

    UserDto updateUser(UserDto dto, Long id);
}
