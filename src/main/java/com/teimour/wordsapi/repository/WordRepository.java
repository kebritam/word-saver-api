package com.teimour.wordsapi.repository;

import com.teimour.wordsapi.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

public interface WordRepository extends JpaRepository<Word, UUID> {

    Optional<Word> findByWordValueIgnoreCase(String word);

    @Query(value = "SELECT * FROM word OFFSET floor(random() * (SELECT count(*) FROM word)) LIMIT 1",
            nativeQuery = true)
    Word findRandomWord();
}
