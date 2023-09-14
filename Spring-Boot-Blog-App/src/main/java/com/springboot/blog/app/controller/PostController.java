package com.springboot.blog.app.controller;

import com.springboot.blog.app.dto.PostDto;
import com.springboot.blog.app.service.FileService;
import com.springboot.blog.app.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/blog/api/v1")
public class PostController {

    private PostService postService;
    private FileService fileService;

    @Value("${upload.image}")
    private String path;

    @Autowired
    public PostController(PostService postService, FileService fileService) {
        this.postService = postService;
        this.fileService = fileService;
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

    @PutMapping("/update/post/{Id}")
    public PostDto updatePost(@PathVariable("Id") Long postId,
                              @RequestBody PostDto postDto) {

        return this.postService.updatePostMethod(postId, postDto);
    }

    // REST API FOR UPLOADING IMAGE
    @PostMapping("/post/image/upload/{postId}")
    public PostDto uploadImage(@PathVariable("postId") Long Id,
                               @RequestParam("image") MultipartFile file) {

        PostDto postDto = this.postService.getPostByIdMethod(Id);
        String newFileName = fileService.uploadImage(file, path);
        postDto.setImageName(newFileName);
        PostDto updatedPost = this.postService.updatePostMethod(Id, postDto);
        return updatedPost;
    }

    // REST API TO DELETE IMAGE
    @DeleteMapping("/post/del/image/{postId}")
    public PostDto deleteImage(@PathVariable("postId") Long Id) {
        PostDto postDto = this.postService.getPostByIdMethod(Id);
        String fileName = postDto.getImageName();
        String fileDeleted = fileService.deleteImageMethod(path, fileName);

        postDto.setImageName(fileDeleted);
        return this.postService.updatePostMethod(Id, postDto);
    }

    // REST API TO DOWNLOAD IMAGE
    @GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream inputStream = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream, response.getOutputStream());
    }
}
