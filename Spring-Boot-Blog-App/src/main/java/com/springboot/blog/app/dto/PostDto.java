package com.springboot.blog.app.dto;

import com.springboot.blog.app.model.Category;
import com.springboot.blog.app.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto extends  BaseModelDto {

    private String name;
    private String description;
    private User user;
    private Category category;
}
