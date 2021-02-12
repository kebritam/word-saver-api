package com.teimour.wordsapi.service;

import com.teimour.wordsapi.domain.Word;
import com.teimour.wordsapi.exception.NotFoundException;
import com.teimour.wordsapi.mapper.WordMapper;
import com.teimour.wordsapi.modelDTO.WordDTO;
import com.teimour.wordsapi.modelDTO.WordListDTO;
import com.teimour.wordsapi.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public WordListDTO findAll() {
        WordMapper wordMapper = WordMapper.INSTANCE;
        return new WordListDTO(
                wordRepository.findAllByOrderByWordValue()
                        .stream()
                        .map(wordMapper::wordToWordDTO)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public List<String> findAllValues() {
        return wordRepository.findAllByOrderByWordValue()
                .stream()
                .map(Word::getWordValue)
                .collect(Collectors.toList())
                ;
    }

}
