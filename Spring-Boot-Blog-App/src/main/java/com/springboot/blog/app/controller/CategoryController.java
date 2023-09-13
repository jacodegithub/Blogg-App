package com.springboot.blog.app.controller;

import com.springboot.blog.app.dto.CategoryDto;
import com.springboot.blog.app.service.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("blog/api/v1")
public class CategoryController {

    private CategoryService catService;

    public CategoryController(CategoryService cat) {
        this.catService = cat;
    }


    @PostMapping("/category")
    public CategoryDto createCategory(@RequestBody CategoryDto catDto) {
        return this.catService.createCategoryMethod(catDto);
    }
}
