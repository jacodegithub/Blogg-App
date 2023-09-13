package com.springboot.blog.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseModelDto {

    private Long id;
    private Date createdDate;
    private Date lastModifiedDate;
}
