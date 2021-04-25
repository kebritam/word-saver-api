package com.teimour.wordsapi.service;

import com.teimour.wordsapi.domain.Category;
import com.teimour.wordsapi.exception.NotFoundException;
import com.teimour.wordsapi.mapper.CategoryMapper;
import com.teimour.wordsapi.modelDTO.CategoryDTO;
import com.teimour.wordsapi.modelDTO.ShallowCategory;
import com.teimour.wordsapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<ShallowCategory> findAllValues() {
        return categoryRepository.findAllByOrderByCategoryName()
                .stream()
                .map(category -> new ShallowCategory(
                        category.getCategoryName(), "/categories/" + category.getCategoryName()
                ))
                .collect(Collectors.toList())
                ;
    }

    @Override
    public CategoryDTO findByName(String name) {
        Optional<Category> categoryOptional = categoryRepository.findByCategoryNameIgnoreCase(name);
        if (categoryOptional.isEmpty()){
            throw new NotFoundException("category not found");
        }
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(categoryOptional.get());
    }

}
