package com.peaceandcode.expensemanager.controller;

import com.peaceandcode.expensemanager.dto.ExpenseDTO;
import com.peaceandcode.expensemanager.entity.Expense;
import com.peaceandcode.expensemanager.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExpenseController {
  private final ExpenseService expenseService;
  @PostMapping("/expenses")
  public ResponseEntity<Expense> createExpense(@Valid @RequestBody ExpenseDTO expenseDTO){
    Expense newExpense = expenseService.createExpense(expenseDTO);
    return new ResponseEntity<>(newExpense,HttpStatus.CREATED);
  }
  @GetMapping("/expenses")
  public ResponseEntity<List<Expense>> getAllExpenses(@RequestParam(required = false) Long ctaegoryId, Pageable pageable){
    List<Expense> expenses = ctaegoryId == null
      ? expenseService.getAllExpenses(pageable)
      : expenseService.getAllExpensesByCategory(ctaegoryId,pageable);

    return new ResponseEntity<>(expenses, HttpStatus.OK);
  }
  @GetMapping("/expenses/{expenseId}")
  public ResponseEntity<Expense> getExpenseById(@PathVariable Long expenseId){
    Expense expense = expenseService.getExpenseById(expenseId);
    return new ResponseEntity<>(expense,HttpStatus.OK);
  }
  @PutMapping("/expenses")
  public ResponseEntity<Expense> updateExpense(@RequestParam Long expenseId, @Valid @RequestBody ExpenseDTO expenseDTO){
    Expense updateExpense = expenseService.updateExpense(expenseDTO,expenseId);
    return new ResponseEntity<>(updateExpense,HttpStatus.OK);
  }
  @DeleteMapping("/expenses")
  public ResponseEntity<Void> deleteExpense(@RequestParam Long expenseId){
    expenseService.deleteExpense(expenseId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
