package com.teimour.wordsapi.service;

import com.teimour.wordsapi.domain.Word;
import com.teimour.wordsapi.exception.NotFoundException;
import com.teimour.wordsapi.modelDTO.*;
import com.teimour.wordsapi.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.teimour.wordsapi.mapper.WordMapper.INSTANCE;
import static java.util.stream.Collectors.toList;

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
        return INSTANCE.wordToWordDTO(queryOptional(word));
    }

    @Override
    public WordDTO getRandomWord() {
        return INSTANCE.wordToWordDTO(wordRepository.findRandomWord(1).get(0));
    }

    @Override
    public WordListDTO getRandomWords(int count) {
        return new WordListDTO(wordRepository.findRandomWord(count)
                .stream()
                .map(INSTANCE::wordToWordDTO)
                .collect(toList())
        );
    }

    @Override
    public DefinitionListDTO findDefinitions(String word) {
        return new DefinitionListDTO(queryOptional(word)
                .getDefinitions()
                .stream()
                .map(INSTANCE::definitionToDefinitionDTO)
                .collect(toList())
        );
    }

    @Override
    public Set<ExampleDTO> findExamples(String word) {
        return queryOptional(word)
                .getDefinitions()
                .stream()
                .map(definition -> new ExampleDTO(definition.getWordClass(), definition.getExamples()))
                .collect(Collectors.toSet())
                ;
    }

    @Override
    public RelatedWordsDTO findRelatedWords(String word) {
        Word wordObject = queryOptional(word);
        return new RelatedWordsDTO(
                extractStringList(wordObject.getSynonyms()),
                extractStringList(wordObject.getAntonyms())
        );
    }

    private List<String> extractStringList(Set<Word> words) {
        return words.stream()
                .map(INSTANCE::wordToString)
                .collect(toList())
                ;
    }

    private Word queryOptional(String word) {
        Optional<Word> optionalWord = wordRepository.findByWordValueIgnoreCase(word);
        if (optionalWord.isEmpty()) {
            throw new NotFoundException("word not found");
        }
        return optionalWord.get();
    }

}
