package com.teimour.wordsapi.mapper;

import com.teimour.wordsapi.domain.Category;
import com.teimour.wordsapi.domain.Word;
import com.teimour.wordsapi.modelDTO.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE= Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);

    default String wordToString(Word word){
        return (word==null) ? null : word.getWordValue();
    }

    default String categoryToString(Category category){
        return (category==null) ? null : category.getCategoryName();
    }
}
