package com.teimour.wordsapi.modelDTO;

import com.teimour.wordsapi.domain.WordClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

@Getter
@Setter
@AllArgsConstructor
public class WordDTO {

    private String wordValue;
    private Set<WordClass> wordClasses;
    private Set<DefinitionDTO> definitions;
    private Set<String> categories;
    private NoteDTO notes;
    private String phonetic;
    private Set<String> synonyms;
    private Set<String> antonyms;
}
