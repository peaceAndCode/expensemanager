package com.peaceandcode.expensemanager.repository;

import com.peaceandcode.expensemanager.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget,Long> {
  Optional<Budget> findBudgetByUserIdAndCategoryId(Long userId, Long categoryId);
}
