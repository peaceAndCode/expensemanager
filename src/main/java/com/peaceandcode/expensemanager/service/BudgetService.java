package com.peaceandcode.expensemanager.service;

import com.peaceandcode.expensemanager.dto.BudgetDTO;
import com.peaceandcode.expensemanager.entity.Budget;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BudgetService {
  Budget getBudget(Long id);
  List<Budget> getAllBudgets(Pageable pageable);
  Budget getBudgetByUserIdAndCategoryId(Long userId, Long categoryId);
  Budget createBudget(BudgetDTO budget);
  Budget updateBudget(BudgetDTO budget, Long id);
  void deleteBudget(Long id);
}
