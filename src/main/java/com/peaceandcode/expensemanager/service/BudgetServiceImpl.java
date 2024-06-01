package com.peaceandcode.expensemanager.service;

import com.peaceandcode.expensemanager.constant.Role;
import com.peaceandcode.expensemanager.dto.BudgetDTO;
import com.peaceandcode.expensemanager.entity.Budget;
import com.peaceandcode.expensemanager.entity.Category;
import com.peaceandcode.expensemanager.entity.User;
import com.peaceandcode.expensemanager.exception.BadRequestException;
import com.peaceandcode.expensemanager.exception.ResourceNotCreated;
import com.peaceandcode.expensemanager.exception.ResourceNotFound;
import com.peaceandcode.expensemanager.repository.BudgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService{
  private final BudgetRepository budgetRepository;
  private final UserService userService;
  private final CategoryService categoryService;
  @Override
  public Budget getBudget(Long id) {
    return budgetRepository.findById(id)
      .orElseThrow(()-> new ResourceNotFound("Budget not found with id: "+id));
  }

  @Override
  public List<Budget> getAllBudgets(Pageable pageable) {
    return budgetRepository.findAll(pageable).toList();
  }

  @Override
  public Budget getBudgetByUserIdAndCategoryId(Long userId, Long categoryId) {
    return budgetRepository.findBudgetByUserIdAndCategoryId(userId,categoryId)
      .orElseThrow(()->new ResourceNotFound("Budget not found with userId "+userId+" and categoryId: "+categoryId));
  }

  @Override
  public Budget createBudget(BudgetDTO budget) {
    User loggedUser = userService.getLoggedUserDetail();
    Long userId = null;

    if(loggedUser.getRole().equals(Role.USER)){
      userId = loggedUser.getId();
    }else{
      userId = budget.getUserId();

      if(userId == null){
        throw new BadRequestException("Budget userId can't be null");
      }
    }

    User user = !Objects.equals(userId, loggedUser.getId())
      ? userService.getUserById(userId) : loggedUser;

    Category category = categoryService.getCategory(budget.getCategoryId());

    Budget budgetToSave = Budget
      .builder()
      .start(budget.getStart())
      .end(budget.getEnd())
      .amount(budget.getAmount())
      .user(user)
      .category(category)
      .build();

    budgetRepository.save(budgetToSave);

    if(budgetToSave.getId() != null){
      return budgetToSave;
    }else{
      throw new ResourceNotCreated("Failed to create new Budget");
    }
  }

  @Override
  public Budget updateBudget(BudgetDTO budget, Long id) {
    User loggedUser = userService.getLoggedUserDetail();
    Budget currentBudget = getBudget(id);
    Date start = Objects.equals(currentBudget.getStart(),budget.getStart()) ? currentBudget.getStart() : budget.getStart();
    Date end = Objects.equals(currentBudget.getEnd(),budget.getEnd()) ? currentBudget.getEnd() : budget.getEnd();
    Double amount = Objects.equals(currentBudget.getAmount(),budget.getAmount()) ? currentBudget.getAmount() : budget.getAmount();
   Category category = Objects.equals(currentBudget.getCategory().getId(), budget.getCategoryId()) ? currentBudget.getCategory() : categoryService.getCategory(budget.getCategoryId());
    Long userId = null;

    if(loggedUser.getRole().equals(Role.USER)){
      userId = loggedUser.getId();
    }else{
      userId = budget.getUserId();

      if(userId == null){
        throw new BadRequestException("Budget userId can't be null");
      }
    }

    User user = !Objects.equals(userId, loggedUser.getId())
      ? userService.getUserById(userId) : loggedUser;

    currentBudget = Budget
      .builder()
      .start(start)
      .end(end)
      .amount(amount)
      .category(category)
      .user(user)
      .build();

    budgetRepository.save(currentBudget);

    if(currentBudget.getId() != null){
      return currentBudget;
    }else{
      throw new ResourceNotCreated("Failed to create new Budget");
    }
  }

  @Override
  public void deleteBudget(Long id) {
    Budget budget = budgetRepository.findById(id)
      .orElseThrow(()->new ResourceNotFound("Budget not found with id: "+id));

    budgetRepository.delete(budget);
  }
}
