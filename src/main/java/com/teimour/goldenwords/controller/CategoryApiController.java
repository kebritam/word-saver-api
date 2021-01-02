package com.teimour.goldenwords.controller;

import com.teimour.goldenwords.service.CategoryServiceDTO;
import com.teimour.goldenwords.modelDTO.CategoryDTO;
import com.teimour.goldenwords.modelDTO.CategoryListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

@RestController
@RequestMapping("/category")
public class CategoryApiController {

    private final CategoryServiceDTO categoryService;

    public CategoryApiController(CategoryServiceDTO categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getCategories(){
        return new CategoryListDTO(categoryService.findAll());
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategory(@PathVariable String name){
        return categoryService.findByName(name);
    }

}
