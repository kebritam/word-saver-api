package com.teimour.goldenwords.service;

import com.teimour.goldenwords.modelDTO.CategoryDTO;

import java.util.Set;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

public interface CategoryServiceDTO {

    Set<CategoryDTO> findAll();

    CategoryDTO findByName(String name);

}
