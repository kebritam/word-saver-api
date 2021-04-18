package com.teimour.wordsapi.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author kebritam
 * Project words-api
 * Created on 18/04/2021
 */

@Getter
@Setter
@AllArgsConstructor
public class DefinitionListDTO {
    List<DefinitionDTO> definitions;
}
