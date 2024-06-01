package com.peaceandcode.expensemanager.repository;

import com.peaceandcode.expensemanager.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long> {
  Optional<Budget> findBudgetByUserIdAndCategoryId(Long userId, Long categoryId);
}
