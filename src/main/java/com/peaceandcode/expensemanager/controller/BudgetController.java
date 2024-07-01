package com.peaceandcode.expensemanager.controller;

import com.peaceandcode.expensemanager.constant.Role;
import com.peaceandcode.expensemanager.dto.BudgetDTO;
import com.peaceandcode.expensemanager.entity.Budget;
import com.peaceandcode.expensemanager.entity.User;
import com.peaceandcode.expensemanager.service.BudgetService;
import com.peaceandcode.expensemanager.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BudgetController {
  private final BudgetService budgetService;
  private final UserService userService;
  @Secured("ADMIN")
  @GetMapping("/budgets/{budgetId}")
  public ResponseEntity<BudgetDTO> getBudget(@PathVariable Long budgetId){
    BudgetDTO budget = budgetService.getBudget(budgetId);
    return new ResponseEntity<>(budget, HttpStatus.OK);
  }
  @Secured("ADMIN")
  @GetMapping("/budgets")
  public ResponseEntity<List<BudgetDTO>> getAllBudgets(Pageable pageable){
    List<BudgetDTO> budgets = budgetService.getAllBudgets(pageable);
    return new ResponseEntity<>(budgets,HttpStatus.OK);
  }
  @GetMapping("/budgets/category")
  public ResponseEntity<BudgetDTO> getBudgetByUserIdAndCategoryId(@RequestParam(required = false) Long userId, @RequestParam Long categoryId){
    User loggedUser = userService.getLoggedUserDetail();
    Long userIdToUse = loggedUser.getRole().equals(Role.USER) ? loggedUser.getId() : userId;
    BudgetDTO budget = budgetService.getBudgetByUserIdAndCategoryId(userIdToUse,categoryId);

    return new ResponseEntity<>(budget,HttpStatus.OK);
  }
  @PostMapping("/budgets")
  public ResponseEntity<BudgetDTO> createBudget(@Valid @RequestBody BudgetDTO budgetDTO){
    BudgetDTO budget = budgetService.createBudget(budgetDTO);
    return new ResponseEntity<>(budget,HttpStatus.CREATED);
  }
  @PutMapping("/budgets")
  public ResponseEntity<BudgetDTO> updateBudget(@Valid @RequestBody BudgetDTO budgetDTO, @RequestParam Long budgetId){
    BudgetDTO budget = budgetService.updateBudget(budgetDTO,budgetId);
    return new ResponseEntity<>(budget,HttpStatus.OK);
  }
  @DeleteMapping("/budgets")
  public ResponseEntity<Void> deleteBudget(@RequestParam Long budgetId){
    budgetService.deleteBudget(budgetId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
