package com.teimour.wordsapi.controller;

import com.teimour.wordsapi.modelDTO.ShallowCategory;
import com.teimour.wordsapi.service.CategoryServiceDTO;
import com.teimour.wordsapi.modelDTO.CategoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

@RestController
@RequestMapping("/categories")
public class CategoryApiController {

    private final CategoryServiceDTO categoryService;

    public CategoryApiController(CategoryServiceDTO categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ShallowCategory> getCategories(){
        return categoryService.findAllValues();
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategory(@PathVariable String name){
        return categoryService.findByName(name);
    }

}
