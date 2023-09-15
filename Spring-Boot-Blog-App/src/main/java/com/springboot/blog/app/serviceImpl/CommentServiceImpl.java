package com.springboot.blog.app.serviceImpl;

import com.springboot.blog.app.dto.CommentDto;
import com.springboot.blog.app.exception.ResourceNotFoundException;
import com.springboot.blog.app.model.Comment;
import com.springboot.blog.app.repository.CommentRepository;
import com.springboot.blog.app.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepo;
    private ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepo, ModelMapper modelMapper) {
        this.commentRepo = commentRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setCreatedDate(new Date());
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Long Id) {
        Comment comment = this.commentRepo.findById(Id).orElseThrow(
                () -> new ResourceNotFoundException("comment", Id)
        );
    }
}
