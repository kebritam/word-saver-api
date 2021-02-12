package com.teimour.wordsapi.modelDTO;

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
public class CategoryDTO {

    private String categoryName;
    private Set<WordDTO> words;
}
