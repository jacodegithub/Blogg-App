package com.springboot.blog.app.serviceImpl;

import com.springboot.blog.app.dto.UserDto;
import com.springboot.blog.app.exception.ResourceNotFoundException;
import com.springboot.blog.app.model.User;
import com.springboot.blog.app.repository.UserRepository;
import com.springboot.blog.app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        user.setEmailId(userDto.getEmailId());
        user.setPassword(userDto.getPassword());
        user.setCreatedDate(new Date());

        User savedUser = userRepo.save(user);

        UserDto dto = modelMapper.map(savedUser, UserDto.class);
        return dto;
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDto = users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto dto, Long userId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException(dto.getUserName(), userId)
        );
        if(user.getCreatedDate() != null) {
            user.setLastModifiedDate(new Date());
        }

        user.setId(dto.getId());
        user.setUserName(dto.getUserName());
        user.setEmailId(dto.getEmailId());
        user.setPassword(dto.getPassword());

        User updatedUser = userRepo.save(user);

        return modelMapper.map(updatedUser, UserDto.class);
    }

}
