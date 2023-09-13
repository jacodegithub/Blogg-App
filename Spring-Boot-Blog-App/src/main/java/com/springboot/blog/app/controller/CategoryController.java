package com.springboot.blog.app.controller;

import com.springboot.blog.app.dto.CategoryDto;
import com.springboot.blog.app.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/categories")
    public List<CategoryDto> getAlCategories() {
        return this.catService.getAllCategoriesMethod();
    }

    @GetMapping("/get-category/{Id}")
    public CategoryDto getCategoryById(@PathVariable("Id") Long Id) {
        return this.catService.getCategoryByIdMethod(Id);
    }

    @PutMapping("/update-category/{Id}")
    public CategoryDto updateCategoryById(@PathVariable("Id") Long catId,
                                          @RequestBody CategoryDto dto) {
        return this.catService.updateCategoryMethod(dto, catId);
    }

    @DeleteMapping("/del-category/{Id}")
    public String deleteCategory(@PathVariable("Id") Long catId) {
        return this.catService.deleteCategoryMethod(catId);
    }
}
