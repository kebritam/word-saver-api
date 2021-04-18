package com.teimour.wordsapi.mapper;

import com.teimour.wordsapi.domain.Category;
import com.teimour.wordsapi.domain.Definition;
import com.teimour.wordsapi.domain.Word;
import com.teimour.wordsapi.modelDTO.DefinitionDTO;
import com.teimour.wordsapi.modelDTO.WordDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

@Mapper
public interface WordMapper {

    WordMapper INSTANCE= Mappers.getMapper(WordMapper.class);

    WordDTO wordToWordDTO(Word word);

    DefinitionDTO definitionToDefinitionDTO(Definition definition);

    default String wordToString(Word word){
        return (word == null) ? null : word.getWordValue();
    }

    default String categoryToString(Category category){
        return (category==null) ? null : category.getCategoryName();
    }
}
