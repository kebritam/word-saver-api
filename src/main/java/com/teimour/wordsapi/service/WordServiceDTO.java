package com.teimour.wordsapi.service;

import com.teimour.wordsapi.modelDTO.WordDTO;
import com.teimour.wordsapi.modelDTO.WordListDTO;

import java.util.List;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

public interface WordServiceDTO {

    WordDTO findByWord(String word);

    WordListDTO findAll();

    List<String> findAllValues();
}
