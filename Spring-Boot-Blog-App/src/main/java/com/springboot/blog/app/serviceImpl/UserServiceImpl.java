package com.springboot.blog.app.serviceImpl;

import com.springboot.blog.app.dto.UserDto;
import com.springboot.blog.app.model.User;
import com.springboot.blog.app.repository.UserRepository;
import com.springboot.blog.app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto creteUser(UserDto userDto) {
        User user = new User();
        user.setCreatedDate(new Date());
        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        user.setEmailId(userDto.getEmailId());
        user.setPassword(userDto.getPassword());

        User savedUser = userRepo.save(user);

        UserDto dto = modelMapper.map(savedUser, UserDto.class);
        return dto;
    }
}
