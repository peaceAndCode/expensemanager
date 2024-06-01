package com.peaceandcode.expensemanager.service;

import com.peaceandcode.expensemanager.dto.ExpenseDTO;
import com.peaceandcode.expensemanager.entity.Expense;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ExpenseService {
  Expense createExpense(ExpenseDTO expense);
  List<Expense> getAllExpenses(Pageable pageable);
  List<Expense> getAllExpensesByCategory(Long categoryId,Pageable pageable);
  Expense getExpenseById(Long expenseId);
  Expense updateExpense(ExpenseDTO updatedExpense, Long expenseId);
  void deleteExpense(Long expenseId);
}
