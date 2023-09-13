package com.springboot.blog.app.serviceImpl;

import com.springboot.blog.app.dto.UserDto;
import com.springboot.blog.app.model.User;
import com.springboot.blog.app.repository.UserRepository;
import com.springboot.blog.app.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;

    @Override
    public UserDto creteUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        user.setEmailId(userDto.getEmailId());
        user.setPassword(userDto.getPassword());

        userRepo.save(user);

        return userDto;
    }
}
