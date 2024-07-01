package com.peaceandcode.expensemanager.service;

import com.peaceandcode.expensemanager.entity.Category;
import com.peaceandcode.expensemanager.exception.ResourceNotCreated;
import com.peaceandcode.expensemanager.exception.ResourceNotFound;
import com.peaceandcode.expensemanager.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
  private final CategoryRepository categoryRepository;
  @Override
  public Category getCategory(Long id) {
    return categoryRepository.findById(id)
      .orElseThrow(()-> new ResourceNotFound("Category not found with id: "+id));
  }

  @Override
  public List<Category> getAllCategories(Pageable pageable) {
    return categoryRepository.findAll(pageable).toList();
  }

  @Override
  public Category createCategory(Category category) {
    Category categorySaved = categoryRepository.save(category);

    if(categorySaved.getId() != null){
      return categorySaved;
    }else{
      throw new ResourceNotCreated("Failed to create new category");
    }

  }

  @Override
  public Category updateCategory(Category category, Long id) {
    Category categoryToUpdate = categoryRepository.findById(id)
      .orElseThrow(()-> new ResourceNotFound("Category not found with id: "+id));

    String name = Objects.equals(categoryToUpdate.getName(),category.getName())
      ? categoryToUpdate.getName()
      : category.getName();

    categoryToUpdate.setName(name);

    return categoryRepository.save(categoryToUpdate);

  }

  @Override
  public void deleteCategory(Long id) {
    Category category = categoryRepository.findById(id)
      .orElseThrow(()-> new ResourceNotFound("Category not found with id: "+id));

    categoryRepository.delete(category);
  }
}
