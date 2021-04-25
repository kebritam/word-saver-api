package com.teimour.wordsapi.controller;

import com.teimour.wordsapi.modelDTO.*;
import com.teimour.wordsapi.service.WordServiceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

@RestController
@RequestMapping("/words")
@ResponseStatus(HttpStatus.OK)
public class WordApiController {

    private final WordServiceDTO wordService;

    public WordApiController(WordServiceDTO wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/random")
    public WordDTO randomWords() {
        return wordService.getRandomWord();
    }

    @GetMapping("/search/{word}")
    public WordDTO searchWord(@PathVariable String word) {
        return wordService.findByWord(word);
    }

    @GetMapping("/search/{word}/definitions")
    public DefinitionListDTO findDefinitions(@PathVariable("word") String word) {
        return wordService.findDefinitions(word);
    }

    @GetMapping("/search/{word}/examples")
    public Set<ExampleDTO> findExamples(@PathVariable("word") String word) {
        return wordService.findExamples(word);
    }

    @GetMapping("/search/{word}/related")
    public RelatedWordsDTO relatedWords(@PathVariable("word") String word) {
        return wordService.findRelatedWords(word);
    }

    @GetMapping("/search/{word}/definitions/{uuid}")
    public DefinitionDTO findDefinition(@PathVariable("word") String word, @PathVariable("uuid") String uuid) {
        return wordService.findDefinition(word, uuid);
    }
}
