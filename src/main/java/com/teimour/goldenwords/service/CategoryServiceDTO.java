package com.teimour.goldenwords.service;

import com.teimour.goldenwords.modelDTO.CategoryDTO;

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
