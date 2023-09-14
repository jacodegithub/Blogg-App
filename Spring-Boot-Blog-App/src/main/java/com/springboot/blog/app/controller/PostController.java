package com.springboot.blog.app.controller;

import com.springboot.blog.app.dto.PostDto;
import com.springboot.blog.app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog/api/v1")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping("/user/{userId}/category/{categoryId}/create-post")
    public PostDto createPost(@PathVariable("userId") Long userId,
                              @PathVariable("categoryId") Long catId,
                              @RequestBody PostDto postDto) {

        PostDto post = this.postService.createPost(userId, catId, postDto);
        return post;
    }

    @GetMapping("/posts")
    public List<PostDto> getAllPosts() {
        List<PostDto> posts = postService.getAllPostsMethod();
        return posts;
    }

    @GetMapping("/posts/user/{Id}")
    public  List<PostDto> getAllPostsByUserId(@PathVariable("Id") Long userId) {
        List<PostDto> posts = postService.getPostByUserIdMethod(userId);
        return posts;
    }

    @GetMapping("posts/category/{Id}")
    public List<PostDto> getAllPostsByCatId(@PathVariable("Id") Long catId) {
        List<PostDto> posts = postService.getPostByCategoryIdMethod(catId);
        return posts;
    }

    @GetMapping("post/{Id}")
    public PostDto getPostById(@PathVariable("Id") Long postId) {
        return this.postService.getPostByIdMethod(postId);
    }

    @PutMapping("/user/{userId}/category/{catId}/post/{Id}")
    public PostDto updatePost(@PathVariable("Id") Long postId,
                              @RequestBody PostDto postDto) {

        return this.postService.updatePostMethod(postId, postDto);
    }
}
