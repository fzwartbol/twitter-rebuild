package com.frederikzwartbol.springbootjpamanytomany.repository;

import com.frederikzwartbol.springbootjpamanytomany.models.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String message);
}
