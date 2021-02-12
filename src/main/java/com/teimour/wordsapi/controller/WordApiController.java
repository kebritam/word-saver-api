package com.teimour.wordsapi.controller;

import com.teimour.wordsapi.service.WordServiceDTO;
import com.teimour.wordsapi.modelDTO.WordDTO;
import com.teimour.wordsapi.modelDTO.WordListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

@RestController
public class WordApiController {

    private final WordServiceDTO wordService;

    public WordApiController(WordServiceDTO wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/words")
    @ResponseStatus(HttpStatus.OK)
    public WordListDTO getWords() {
        return wordService.findAll();
    }

    @GetMapping("/word/{value}")
    @ResponseStatus(HttpStatus.OK)
    public WordDTO getWord(@PathVariable String value){
        return wordService.findByWord(value);
    }

    @GetMapping("/word-values")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getWordValues() {
        return wordService.findAllValues();
    }
}
