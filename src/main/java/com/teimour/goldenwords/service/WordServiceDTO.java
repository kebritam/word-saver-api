package com.teimour.goldenwords.service;

import com.teimour.goldenwords.modelDTO.WordDTO;

import java.util.Set;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

public interface WordServiceDTO {

    WordDTO findByWord(String word);

    Set<WordDTO> findAll();

}
