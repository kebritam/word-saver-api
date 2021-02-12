package com.teimour.wordsapi.repository;

import com.teimour.wordsapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    List<Category> findAllByOrderByCategoryName();

    Optional<Category> findByCategoryNameIgnoreCase(String name);
}
