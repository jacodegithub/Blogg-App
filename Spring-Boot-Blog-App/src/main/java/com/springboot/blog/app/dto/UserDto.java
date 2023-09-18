package com.springboot.blog.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseModelDto {
    @NotNull
    private String username;
    @NotNull
    @Email(message = "Email address is not valid!!")
    private String emailId;
    @NotEmpty(message = "Password should not be empty!!")
    private String password;
}
