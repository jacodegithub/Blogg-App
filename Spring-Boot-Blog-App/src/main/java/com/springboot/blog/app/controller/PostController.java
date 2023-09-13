package com.springboot.blog.app.controller;

import com.springboot.blog.app.dto.PostDto;
import com.springboot.blog.app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog/api/v1")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping("/{userId}/{categoryId}/create-post")
    public PostDto createPost(@PathVariable("userId") Long userId,
                              @PathVariable("categoryId") Long catId,
                              @RequestBody PostDto postDto) {
        return this.postService.createPost(userId, catId, postDto);
    }
}
