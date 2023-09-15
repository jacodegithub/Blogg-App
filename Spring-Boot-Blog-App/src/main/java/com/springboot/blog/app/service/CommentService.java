package com.springboot.blog.app.service;

import com.springboot.blog.app.dto.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto);

    void deleteComment(Long Id);
}
