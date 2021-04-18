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
public class WordApiController {

    private final WordServiceDTO wordService;

    public WordApiController(WordServiceDTO wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/random")
    @ResponseStatus(HttpStatus.OK)
    public WordListDTO randomWords(@RequestParam(value = "random-count", defaultValue = "1") int count) {
        return wordService.getRandomWords(count);
    }

    @GetMapping("/{word}")
    @ResponseStatus(HttpStatus.OK)
    public WordDTO searchWord(@PathVariable String word) {
        return wordService.findByWord(word);
    }

    @GetMapping("/{word}/definitions")
    @ResponseStatus(HttpStatus.OK)
    public DefinitionListDTO findDefinitions(@PathVariable("word") String word) {
        return wordService.findDefinitions(word);
    }

    @GetMapping("/{word}/examples")
    @ResponseStatus(HttpStatus.OK)
    public Set<ExampleDTO> findExamples(@PathVariable("word") String word) {
        return wordService.findExamples(word);
    }

    @GetMapping("/{word}/related")
    @ResponseStatus(HttpStatus.OK)
    public RelatedWordsDTO relatedWords(@PathVariable("word") String word) {
        return wordService.findRelatedWords(word);
    }
}
