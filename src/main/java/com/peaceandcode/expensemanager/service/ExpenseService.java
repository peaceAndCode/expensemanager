package com.peaceandcode.expensemanager.service;

import com.peaceandcode.expensemanager.dto.ExpenseDTO;
import com.peaceandcode.expensemanager.entity.Expense;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ExpenseService {
  ExpenseDTO createExpense(ExpenseDTO expense);
  List<ExpenseDTO> getAllExpenses(Pageable pageable);
  List<ExpenseDTO> getAllExpensesByCategory(Long categoryId,Pageable pageable);
  ExpenseDTO getExpenseById(Long expenseId);
  ExpenseDTO updateExpense(ExpenseDTO updatedExpense, Long expenseId);
  void deleteExpense(Long expenseId);
}
