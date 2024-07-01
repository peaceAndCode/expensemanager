package com.peaceandcode.expensemanager.service;

import com.peaceandcode.expensemanager.dto.BudgetDTO;
import com.peaceandcode.expensemanager.entity.Budget;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BudgetService {
  BudgetDTO getBudget(Long id);
  List<BudgetDTO> getAllBudgets(Pageable pageable);
  BudgetDTO getBudgetByUserIdAndCategoryId(Long userId, Long categoryId);
  BudgetDTO createBudget(BudgetDTO budget);
  BudgetDTO updateBudget(BudgetDTO budget, Long id);
  void deleteBudget(Long id);
}
