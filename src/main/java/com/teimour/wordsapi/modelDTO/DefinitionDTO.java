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
public class DefinitionDTO {

    private WordClass wordClass;
    private String definitionValue;
    private Set<String> examples;
}
