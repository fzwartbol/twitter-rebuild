package com.frederikzwartbol.springbootjpamanytomany.controller.category;

import com.frederikzwartbol.springbootjpamanytomany.models.entity.Category;
import com.frederikzwartbol.springbootjpamanytomany.models.request.CategoryRequest;
import com.frederikzwartbol.springbootjpamanytomany.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController implements CategoryOperations{

    private final CategoryService categoryService;

    @Override
    public ResponseEntity<Category> saveCategory(CategoryRequest request) {
        return ResponseEntity.ok(categoryService.saveCategory(request.categoryName()));
    }

    @Override
    public ResponseEntity<?> deleteCategoryByName(String categoryName) {
        categoryService.deleteCategory(categoryName);
        return ResponseEntity.ok("Category deleted");
    }

    @Override
    public ResponseEntity<?> followCategory(String category, Long userId) {
        return ResponseEntity.ok(categoryService.followCategory(category,userId));
    }

    @Override
    public ResponseEntity<?> unfollowCategory(String category, Long userId) {
        return ResponseEntity.ok(categoryService.unfollowCategory(category,userId));
    }

}
