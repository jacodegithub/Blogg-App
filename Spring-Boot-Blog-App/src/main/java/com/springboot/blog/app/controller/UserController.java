package com.springboot.blog.app.controller;

import com.springboot.blog.app.dto.UserDto;
import com.springboot.blog.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog/api/v1")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.creteUser(userDto);
    }
}
