package com.frederikzwartbol.springbootjpamanytomany.controller.category;

import com.frederikzwartbol.springbootjpamanytomany.models.entity.Category;
import com.frederikzwartbol.springbootjpamanytomany.models.request.CategoryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(CategoryOperations.PREFIX)
public interface CategoryOperations {
    String PREFIX = "/category";

    @PostMapping
    ResponseEntity<Category> saveCategory (@RequestBody CategoryRequest request);

    @DeleteMapping("/{categoryName}")
    ResponseEntity<?> deleteCategoryByName (@PathVariable("categoryName") String categoryName);

    @PostMapping("/{categoryName}/follow/{userId}")
    ResponseEntity<?> followCategory (@PathVariable("categoryName") String Category, @PathVariable("userId") Long userId);

    @PostMapping("/{categoryName}/unfollow/{userId}")
    ResponseEntity<?> unfollowCategory (@PathVariable("categoryName") String Category, @PathVariable("userId") Long userId);

}
