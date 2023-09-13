package com.springboot.blog.app.service;

import com.springboot.blog.app.dto.PostDto;

public interface PostService {

    PostDto createPost(Long userId, Long catId, PostDto postDto);
}
