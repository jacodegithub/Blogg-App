package com.springboot.blog.app.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    private String exception;
    private Long id;

    public ResourceNotFoundException(String exception, Long Id) {
        super("Error!! "+exception+" not found with id "+Id);
        this.exception = exception;
        this.id = Id;
    }
}
