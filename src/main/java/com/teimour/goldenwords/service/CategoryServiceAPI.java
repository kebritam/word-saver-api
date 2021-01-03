package com.teimour.goldenwords.service;

import com.teimour.goldenwords.domain.Category;
import com.teimour.goldenwords.exception.NotFoundException;
import com.teimour.goldenwords.mapper.CategoryMapper;
import com.teimour.goldenwords.modelDTO.CategoryDTO;
import com.teimour.goldenwords.repository.CategoryRepository;
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
    public List<String> findAllValues() {
        return categoryRepository.findAllByOrderByCategoryName()
                .stream()
                .map(Category::getCategoryName)
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
