package com.teimour.goldenwords.modelDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CategoryListDTO {

    @JsonProperty("categories")
    private Set<CategoryDTO> categoryDTOSet;
}
