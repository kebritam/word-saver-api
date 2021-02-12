package com.teimour.wordsapi.modelDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

@Getter
@Setter
@AllArgsConstructor
public class WordListDTO {

    @JsonProperty("words")
    private List<WordDTO> wordDTOSet;
}
