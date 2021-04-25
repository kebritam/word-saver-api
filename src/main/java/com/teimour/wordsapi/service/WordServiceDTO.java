package com.teimour.wordsapi.service;

import com.teimour.wordsapi.modelDTO.*;

import java.util.Set;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

public interface WordServiceDTO {

    WordDTO findByWord(String word);

    WordDTO getRandomWord();

    DefinitionListDTO findDefinitions(String word);

    DefinitionDTO findDefinition(String word, String uuid);

    Set<ExampleDTO> findExamples(String word);

    RelatedWordsDTO findRelatedWords(String words);
}
