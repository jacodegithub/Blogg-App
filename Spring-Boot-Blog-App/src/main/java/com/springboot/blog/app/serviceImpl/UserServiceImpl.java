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

        UserDto dto = this.userToDto(savedUser);
        return dto;
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDto = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
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

        // NEVER TRY TO UPDATE PRIMARY KEY
        user.setUserName(dto.getUserName());
        user.setEmailId(dto.getEmailId());
        user.setPassword(dto.getPassword());

        User updatedUser = userRepo.save(user);

        return this.userToDto(updatedUser);
    }


    public UserDto getUserById(Long id) {
        User user =  this.userRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("user", id)
        );

        return this.userToDto(user);
    }

    public String deleteUser(Long id) {
        User user = userRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("user", id)
        );
        userRepo.deleteById(id);
        return "User successfully deleted!.";
    }

    public UserDto userToDto(User user) {
        return this.modelMapper.map(user, UserDto.class);
    }
}
