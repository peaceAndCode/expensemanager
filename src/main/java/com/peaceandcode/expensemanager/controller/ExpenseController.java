package com.peaceandcode.expensemanager.controller;

import com.peaceandcode.expensemanager.constant.OrderingType;
import com.peaceandcode.expensemanager.dto.ExpenseDTO;
import com.peaceandcode.expensemanager.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExpenseController {
  private final ExpenseService expenseService;
  @PostMapping("/expenses")
  public ResponseEntity<ExpenseDTO> createExpense(@Valid @RequestBody ExpenseDTO expenseDTO){
    return new ResponseEntity<>(expenseService.createExpense(expenseDTO),HttpStatus.CREATED);
  }
  @GetMapping("/expenses")
  @Transactional
  public ResponseEntity<List<ExpenseDTO>> getAllExpenses(
          @RequestParam(required = false)
          Long categoryId,
          @PageableDefault(page = 0,size = 20) Pageable pageable,
          @RequestParam(required = false) OrderingType dateFilter,
          @RequestParam(required = false) OrderingType priceFilter
  ){
    List<ExpenseDTO> expenses = categoryId == null
      ? expenseService.getAllExpenses(pageable,dateFilter,priceFilter)
      : expenseService.getAllExpensesByCategory(categoryId,dateFilter,priceFilter,pageable);

    return new ResponseEntity<>(expenses, HttpStatus.OK);
  }
  @GetMapping("/expenses/{expenseId}")
  @Transactional
  public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable Long expenseId){
    ExpenseDTO expense = expenseService.getExpenseById(expenseId);
    return new ResponseEntity<>(expense,HttpStatus.OK);
  }
  @PutMapping("/expenses")
  public ResponseEntity<ExpenseDTO> updateExpense(@RequestParam Long expenseId, @Valid @RequestBody ExpenseDTO expenseDTO){
    ExpenseDTO updateExpense = expenseService.updateExpense(expenseDTO,expenseId);
    return new ResponseEntity<>(updateExpense,HttpStatus.OK);
  }
  @DeleteMapping("/expenses")
  public ResponseEntity<Void> deleteExpense(@RequestParam Long expenseId){
    expenseService.deleteExpense(expenseId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
