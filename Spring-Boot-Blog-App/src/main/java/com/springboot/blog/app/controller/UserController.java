package com.springboot.blog.app.controller;

import com.springboot.blog.app.dto.UserDto;
import com.springboot.blog.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog/api/v1")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.creteUser(userDto);
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getUsers();
    }

    @PutMapping("/update-user/{id}")
    public UserDto updateUser(@RequestBody UserDto dto, @PathVariable("id") Long userId) {
        return userService.updateUser(dto, userId);
    }

    @GetMapping("/get-user/{Id}")
    public UserDto getUserById(@PathVariable("Id") Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") Long userId) {
        return userService.deleteUser(userId);
    }
}
