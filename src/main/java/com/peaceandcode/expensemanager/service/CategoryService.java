package com.peaceandcode.expensemanager.service;

import com.peaceandcode.expensemanager.entity.Category;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CategoryService {
  Category getCategory(Long id);
  List<Category> getAllCategories(Pageable pageable);
  Category createCategory(Category category);
  Category updateCategory(Category category, Long id);
  void deleteCategory(Long id);
}
