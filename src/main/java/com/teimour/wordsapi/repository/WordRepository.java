package com.teimour.wordsapi.repository;

import com.teimour.wordsapi.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

public interface WordRepository extends JpaRepository<Word, UUID> {

    Optional<Word> findByWordValueIgnoreCase(String word);

    @Query(value = "SELECT * FROM word OFFSET random() * (SELECT count(*) FROM word) LIMIT :count",
            nativeQuery = true)
    List<Word> findRandomWord(@Param("count") int count);
}
