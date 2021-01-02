package com.teimour.goldenwords.repository;

import com.teimour.goldenwords.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author kebritam
 * Project golden-words
 * Created on 02/01/2021
 */

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findByCategoryName(String name);
}
