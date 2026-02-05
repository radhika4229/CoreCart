package com.radhika.corecart.service.category;

import com.radhika.corecart.model.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category,Long id);
    void deleteCategoryById(Long id);
}
