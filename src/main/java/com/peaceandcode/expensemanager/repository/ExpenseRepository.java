package com.peaceandcode.expensemanager.repository;

import com.peaceandcode.expensemanager.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
  Page<Expense> findByUserId(Long userId, Pageable pageable);
  Page<Expense> findByUserIdAndCategoryId(Long userId, Long categoryId, Pageable pageable);
  Page<Expense> findByCategoryId(Long categoryId, Pageable pageable);
  Optional<Expense> findByIdAndUserId(Long id, Long userId);
}
