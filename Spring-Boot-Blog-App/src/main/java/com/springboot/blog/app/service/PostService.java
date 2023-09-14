package com.springboot.blog.app.service;

import com.springboot.blog.app.dto.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(Long userId, Long catId, PostDto postDto);

    List<PostDto> getAllPostsMethod();

    PostDto updatePostMethod(Long postId, PostDto dto);

    PostDto getPostByIdMethod(Long Id);

    List<PostDto> getPostByCategoryIdMethod(Long Id);

    List<PostDto> getPostByUserIdMethod(Long Id);
}
