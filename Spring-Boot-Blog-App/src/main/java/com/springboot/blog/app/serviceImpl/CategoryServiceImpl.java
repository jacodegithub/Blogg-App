package com.springboot.blog.app.serviceImpl;

import com.springboot.blog.app.dto.CategoryDto;
import com.springboot.blog.app.exception.ResourceNotFoundException;
import com.springboot.blog.app.model.Category;
import com.springboot.blog.app.repository.CategoryRepository;
import com.springboot.blog.app.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        category.setCreatedDate(new Date());
        Category savedCat = catRepo.save(category);

        return this.categoryToDto(savedCat);
    }

    @Override
    public List<CategoryDto> getAllCategoriesMethod() {
        List<Category> categories = catRepo.findAll();
        return categories.stream().map(this::categoryToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryByIdMethod(Long Id) {
        Category cat = catRepo.findById(Id).orElseThrow(
                () -> new ResourceNotFoundException("category", Id)
        );

        return this.categoryToDto(cat);
    }

    @Override
    public CategoryDto updateCategoryMethod(CategoryDto dto, Long Id) {
        Category cat = catRepo.findById(Id).orElseThrow(
                () -> new ResourceNotFoundException("category", Id)
        );
        cat.setName(dto.getName());
        cat.setLastModifiedDate(new Date());

        Category updatedCat = catRepo.save(cat);
        return this.categoryToDto(updatedCat);
    }

    @Override
    public String deleteCategoryMethod(Long Id) {
        Category cat = catRepo.findById(Id).orElseThrow(
                () -> new ResourceNotFoundException("category", Id)
        );
        catRepo.deleteById(Id);
        return "Successfully deleted the category!.";
    }


    public CategoryDto categoryToDto(Category cat) {
        return this.modelMapper.map(cat, CategoryDto.class);
    }
}
