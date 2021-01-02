package com.teimour.goldenwords.mapper;

import com.teimour.goldenwords.domain.Category;
import com.teimour.goldenwords.domain.Word;
import com.teimour.goldenwords.modelDTO.CategoryDTO;
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
