package com.springboot.blog.app.controller;

import com.springboot.blog.app.dto.CommentDto;
import com.springboot.blog.app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog/api/v1")
public class CommentController {

    private CommentService comService;

    @Autowired
    public CommentController(CommentService comService) {
        this.comService = comService;
    }

    @PostMapping("/comment")
    public CommentDto createComment(CommentDto commentDto) {
        return this.comService.createComment(commentDto);
    }

    @DeleteMapping("/del/comment/{id}")
    public void deleteComment(@PathVariable("id") Long id) {
        this.comService.deleteComment(id);
    }
}
