package com.teimour.goldenwords.service;

import com.teimour.goldenwords.domain.Word;
import com.teimour.goldenwords.exception.NotFoundException;
import com.teimour.goldenwords.mapper.WordMapper;
import com.teimour.goldenwords.modelDTO.WordDTO;
import com.teimour.goldenwords.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

@Service
public class WordServiceAPI implements WordServiceDTO {

    private final WordRepository wordRepository;

    public WordServiceAPI(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public WordDTO findByWord(String word) {
        Optional<Word> optionalWord=wordRepository.findByWordValueIgnoreCase(word);
        if (optionalWord.isEmpty()){
            throw new NotFoundException("word not found");
        }
        return WordMapper.INSTANCE.wordToWordDTO(optionalWord.get());
    }

    @Override
    public Set<WordDTO> findAll() {
        Set<WordDTO> words=new HashSet<>();
        wordRepository.findAll().forEach(word -> words.add(WordMapper.INSTANCE.wordToWordDTO(word)));
        return words;
    }

}
