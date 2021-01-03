package com.teimour.goldenwords.service;

import com.teimour.goldenwords.modelDTO.WordDTO;
import com.teimour.goldenwords.modelDTO.WordListDTO;

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
