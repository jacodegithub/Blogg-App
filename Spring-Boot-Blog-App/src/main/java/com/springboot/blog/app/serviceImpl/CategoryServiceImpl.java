package com.springboot.blog.app.serviceImpl;

import com.springboot.blog.app.dto.CategoryDto;
import com.springboot.blog.app.model.Category;
import com.springboot.blog.app.repository.CategoryRepository;
import com.springboot.blog.app.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository catRepo;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository catRepo, ModelMapper modelMapper) {
        this.catRepo = catRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public CategoryDto createCategoryMethod(CategoryDto catDto) {
        Category category = this.modelMapper.map(catDto, Category.class);
        Category savedCat = catRepo.save(category);

        return this.categoryToDto(savedCat);
    }


    public CategoryDto categoryToDto(Category cat) {
        return this.modelMapper.map(cat, CategoryDto.class);
    }
}
