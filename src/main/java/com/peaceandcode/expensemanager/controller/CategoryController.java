package com.peaceandcode.expensemanager.controller;

import com.peaceandcode.expensemanager.entity.Category;
import com.peaceandcode.expensemanager.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;
  @GetMapping("/categories/{categoryId}")
  public ResponseEntity<Category> getCategory(@PathVariable Long categoryId){
    Category category = categoryService.getCategory(categoryId);
    return new ResponseEntity<>(category, HttpStatus.OK);
  }
  @GetMapping("/categories")
  public ResponseEntity<List<Category>> getAllCategories(Pageable pageable){
    List<Category> categories = categoryService.getAllCategories(pageable);
    return new ResponseEntity<>(categories,HttpStatus.OK);
  }
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/categories")
  public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category){
    Category newCategory = categoryService.createCategory(category);
    return new ResponseEntity<>(newCategory,HttpStatus.OK);
  }
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/categories")
  public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category, @RequestParam Long categoryId){
    Category updatedCategory = categoryService.updateCategory(category,categoryId);
    return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
  }
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/categories/{categoryId}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId){
    categoryService.deleteCategory(categoryId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
