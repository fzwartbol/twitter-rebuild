package com.frederikzwartbol.springboottwitterrebuild.features.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(CategoryOperations.PREFIX)
public interface CategoryOperations {
    String PREFIX = "/categories";

    @GetMapping
    ResponseEntity<List<Category>> getCategories();

    @PostMapping
    ResponseEntity<Category> saveCategory(@RequestBody CategoryRequest request);

    @DeleteMapping("/{categoryName}")
    ResponseEntity<?> deleteCategoryByName(@PathVariable("categoryName") String categoryName);

    @PostMapping("/{categoryName}/follow/{userId}")
    ResponseEntity<?> followCategory(@PathVariable("categoryName") String Category, @PathVariable("userId") Long userId);

    @PostMapping("/{categoryName}/unfollow/{userId}")
    ResponseEntity<?> unfollowCategory(@PathVariable("categoryName") String Category, @PathVariable("userId") Long userId);

}
