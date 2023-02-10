package com.frederikzwartbol.springboottwitterrebuild.features.category;

import com.frederikzwartbol.springboottwitterrebuild.features.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;

    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(String category) {
        return categoryRepository.findByCategoryName(category)
                .orElseGet(() -> categoryRepository.save(new Category(category)));
    }

    public void deleteCategory(String category) {
        categoryRepository.delete(categoryRepository.findByCategoryName(category)
                .orElseThrow(() -> new NoSuchElementException("Category not found")));
    }

    public Category findCategoryByName(String category) {
        return categoryRepository.findByCategoryName(category)
                .orElseThrow(() -> new NoSuchElementException("Category not found."));
    }

    public boolean followCategory(String categoryName, Long userId) {
        Category category = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new NoSuchElementException("Category not found"));
        var user = userService.findUserById(userId);
        category.getInterestedUsers().add(user);
        user.getInterestedCategories().add(category);
        userService.saveUser(user);
        return true;
    }

    public boolean unfollowCategory(String categoryName, Long userId) {
        var category = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new NoSuchElementException("Category not found"));
        var user = userService.findUserById(userId);
        category.getInterestedUsers().remove(user);
        user.getInterestedCategories().remove(category);
        userService.saveUser(user);
        return true;
    }

}
