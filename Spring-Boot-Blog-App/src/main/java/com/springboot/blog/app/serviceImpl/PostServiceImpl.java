package com.springboot.blog.app.serviceImpl;

import com.springboot.blog.app.dto.PostDto;
import com.springboot.blog.app.exception.ResourceNotFoundException;
import com.springboot.blog.app.model.Category;
import com.springboot.blog.app.model.Post;
import com.springboot.blog.app.model.User;
import com.springboot.blog.app.repository.CategoryRepository;
import com.springboot.blog.app.repository.PostRepository;
import com.springboot.blog.app.repository.UserRepository;
import com.springboot.blog.app.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;
    private UserRepository userRepo;
    private CategoryRepository catRepo;
    private ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepo, UserRepository userRepo, CategoryRepository catRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.catRepo = catRepo;
    }

    @Override
    public PostDto createPost(Long userId, Long catId, PostDto postDto) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user", userId)
        );

        Category category = catRepo.findById(catId).orElseThrow(
                () -> new ResourceNotFoundException("category", catId)
        );
        Post post = this.modelMapper.map(postDto, Post.class);

        post.setCreatedDate(new Date());
        post.setCategory(category);
        post.setUser(user);

        Post savedPost = postRepo.save(post);

        return this.postToDto(savedPost);
    }


    public PostDto postToDto(Post post) {
        return this.modelMapper.map(post, PostDto.class);
    }
}
