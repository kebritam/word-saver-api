package com.teimour.wordsapi.modelDTO;

import com.teimour.wordsapi.domain.WordClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author kebritam
 * Project words-api
 * Created on 18/04/2021
 */

@Getter
@Setter
@AllArgsConstructor
public class ExampleDTO {
    private WordClass wordClass;
    private Set<String> examples;
}
