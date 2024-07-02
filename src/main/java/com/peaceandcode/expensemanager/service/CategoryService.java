package com.peaceandcode.expensemanager.service;

import com.peaceandcode.expensemanager.dto.CategoryDTO;
import com.peaceandcode.expensemanager.entity.Category;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CategoryService {
  CategoryDTO getCategory(Long id);
  List<CategoryDTO> getAllCategories(Pageable pageable);
  CategoryDTO createCategory(Category category);
  CategoryDTO updateCategory(Category category, Long id);
  void deleteCategory(Long id);
}
