package com.teimour.goldenwords.controller;

import com.teimour.goldenwords.service.WordServiceDTO;
import com.teimour.goldenwords.modelDTO.WordDTO;
import com.teimour.goldenwords.modelDTO.WordListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public WordListDTO getAllWords(){
        return new WordListDTO(wordService.findAll());
    }

    @GetMapping("/word/{value}")
    @ResponseStatus(HttpStatus.OK)
    public WordDTO getWord(@PathVariable String value){
        return wordService.findByWord(value);
    }
}
