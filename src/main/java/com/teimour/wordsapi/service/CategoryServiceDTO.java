package com.teimour.wordsapi.service;

import com.teimour.wordsapi.modelDTO.CategoryDTO;

import java.util.List;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

public interface CategoryServiceDTO {

    List<String> findAllValues();

    CategoryDTO findByName(String name);
}
