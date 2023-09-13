package com.springboot.blog.app.service;

import com.springboot.blog.app.dto.CategoryDto;
import com.springboot.blog.app.model.Category;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategoryMethod(CategoryDto catDto);

    List<CategoryDto> getAllCategoriesMethod();

    CategoryDto getCategoryByIdMethod(Long Id);

    CategoryDto updateCategoryMethod(CategoryDto dto, Long Id);

    String deleteCategoryMethod(Long Id);
}
