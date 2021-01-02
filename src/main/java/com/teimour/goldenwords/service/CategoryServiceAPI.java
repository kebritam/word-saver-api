package com.teimour.goldenwords.service;

import com.teimour.goldenwords.domain.Category;
import com.teimour.goldenwords.exception.NotFoundException;
import com.teimour.goldenwords.mapper.CategoryMapper;
import com.teimour.goldenwords.modelDTO.CategoryDTO;
import com.teimour.goldenwords.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

@Service
public class CategoryServiceAPI implements CategoryServiceDTO {

    private final CategoryRepository categoryRepository;

    public CategoryServiceAPI(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<CategoryDTO> findAll() {
        Set<CategoryDTO> categories=new HashSet<>();
        categoryRepository.findAll().forEach(
                category -> categories.add(CategoryMapper.INSTANCE.categoryToCategoryDTO(category))
        );
        return categories;
    }

    @Override
    public CategoryDTO findByName(String name) {
        Optional<Category> categoryOptional=categoryRepository.findByCategoryName(name);
        if (categoryOptional.isEmpty()){
            throw new NotFoundException("category not found");
        }
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(categoryOptional.get());
    }

}
