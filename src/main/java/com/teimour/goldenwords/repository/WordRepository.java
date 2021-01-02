package com.teimour.goldenwords.repository;

import com.teimour.goldenwords.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

public interface WordRepository extends JpaRepository<Word, UUID> {

    Optional<Word> findByWordValueIgnoreCase(String word);
}
