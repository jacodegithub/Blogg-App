package com.springboot.blog.app.controller;

import com.springboot.blog.app.dto.JWTAuthRequest;
import com.springboot.blog.app.dto.JWTAuthResponse;
import com.springboot.blog.app.dto.UserDto;
import com.springboot.blog.app.security.JwtTokenGenerator;
import com.springboot.blog.app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog/api/v1")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;
    private UserService userService;
    private JwtTokenGenerator jwtTokenGenerator;
    private ModelMapper modelMapper;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder,
                          UserDetailsService userDetailsService,
                          UserService userService,
                          JwtTokenGenerator jwtTokenGenerator,
                          ModelMapper modelMapper) {

        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    public UserDto userRegistration(@RequestBody UserDto userDto) {
        return this.userService.creteUser(userDto);
    }

    @PostMapping("/login")
    public JWTAuthResponse userLogin(@RequestBody JWTAuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenGenerator.generateToken(authentication);
        JWTAuthResponse response = new JWTAuthResponse();
        response.setToken(token);
        response.setUserDto(this.modelMapper.map(userDetails, UserDto.class));
        return response;
    }
}
