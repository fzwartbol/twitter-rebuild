package com.frederikzwartbol.springboottwitterrebuild.features.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController implements CategoryOperations {
    private final CategoryService categoryService;

    @Override
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.findCategories());
    }

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
        return ResponseEntity.ok(categoryService.followCategory(category, userId));
    }

    @Override
    public ResponseEntity<?> unfollowCategory(String category, Long userId) {
        return ResponseEntity.ok(categoryService.unfollowCategory(category, userId));
    }

}
