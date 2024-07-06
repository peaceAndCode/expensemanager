package com.peaceandcode.expensemanager.controller;

import com.peaceandcode.expensemanager.dto.CategoryDTO;
import com.peaceandcode.expensemanager.entity.Category;
import com.peaceandcode.expensemanager.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;
  @GetMapping("/categories/{categoryId}")
  public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long categoryId){
    CategoryDTO category = categoryService.getCategory(categoryId);
    return new ResponseEntity<>(category, HttpStatus.OK);
  }
  @GetMapping("/categories")
  public ResponseEntity<List<CategoryDTO>> getAllCategories(Pageable pageable){
    List<CategoryDTO> categories = categoryService.getAllCategories(pageable);
    return new ResponseEntity<>(categories,HttpStatus.OK);
  }
  @Secured("ADMIN")
  @PostMapping("/categories")
  public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody Category category){
    CategoryDTO newCategory = categoryService.createCategory(category);
    return new ResponseEntity<>(newCategory,HttpStatus.OK);
  }
  @Secured("ADMIN")
  @PutMapping("/categories")
  public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody Category category, @RequestParam Long categoryId){
    CategoryDTO updatedCategory = categoryService.updateCategory(category,categoryId);
    return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
  }
  @Secured("ADMIN")
  @DeleteMapping("/categories/{categoryId}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId){
    categoryService.deleteCategory(categoryId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
