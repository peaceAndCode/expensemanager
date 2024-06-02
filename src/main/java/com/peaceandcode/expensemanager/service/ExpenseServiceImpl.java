package com.peaceandcode.expensemanager.service;

import com.peaceandcode.expensemanager.constant.Role;
import com.peaceandcode.expensemanager.dto.ExpenseDTO;
import com.peaceandcode.expensemanager.entity.Category;
import com.peaceandcode.expensemanager.entity.Expense;
import com.peaceandcode.expensemanager.entity.User;
import com.peaceandcode.expensemanager.exception.BadRequestException;
import com.peaceandcode.expensemanager.exception.ResourceNotCreated;
import com.peaceandcode.expensemanager.exception.ResourceNotFound;
import com.peaceandcode.expensemanager.repository.CategoryRepository;
import com.peaceandcode.expensemanager.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
  private final ExpenseRepository expenseRepository;
  private final UserService userService;
  private final CategoryRepository categoryRepository;
  @Override
  public Expense createExpense(ExpenseDTO expense) {
    User loggedUser = userService.getLoggedUserDetail();
    Category category = categoryRepository.findById(expense.getCategoryId())
        .orElseThrow(()-> new ResourceNotFound("Category not found with id: "+expense.getCategoryId()));

    User user = null;

    if(loggedUser.getRole().equals(Role.USER)){
      user = loggedUser;
    }else{
      if(expense.getUserId() == null){
        throw new BadRequestException("UserId can't be null");
      }
      user = userService.getUserById(expense.getUserId());
    }

    Expense newExpense = Expense
      .builder()
      .title(expense.getTitle())
      .description(expense.getDescription())
      .amount(expense.getAmount())
      .receipt(expense.getReceipt())
      .user(user)
      .category(category)
      .build();

    Expense expenseCreated = expenseRepository.save(newExpense);

    if(expenseCreated.getId()!= null){
      return expenseCreated;
    }

    throw new ResourceNotCreated("Failed to create new Expense");
  }

  @Override
  public List<Expense> getAllExpenses(Pageable pageable) {
    User loggedUser = userService.getLoggedUserDetail();

    List<Expense> expenseList = null;

    //Admin must see all expenses of all users
    if(loggedUser.getRole().equals(Role.USER)){
      expenseList = expenseRepository.findByUserId(loggedUser.getId(),pageable).toList();
    }else{
      expenseList = expenseRepository.findAll();
    }

    return expenseList;
  }

  @Override
  public List<Expense> getAllExpensesByCategory(Long categoryId,Pageable pageable) {
    User loggedUser =  userService.getLoggedUserDetail();

    List<Expense> expenseList = null;

    if(loggedUser.getRole().equals(Role.USER)){
      expenseList = expenseRepository.findByUserIdAndCategoryId(loggedUser.getId(), categoryId, pageable).toList();
    }else{
      expenseList = expenseRepository.findByCategoryId(categoryId,pageable).toList();
    }

    return expenseList;
  }

  @Override
  public Expense getExpenseById(Long expenseId) {
    User loggedUser = userService.getLoggedUserDetail();

    Expense expense = expenseRepository.findById(expenseId)
      .orElseThrow(() -> new ResourceNotFound("Expense not found with id: "+expenseId));

    if(!Objects.equals(expense.getUser().getId(), loggedUser.getId())){
      throw new ResourceNotFound("Expense not found with id: "+expenseId+ " and userId: "+loggedUser.getId());
    }
    return expense;
  }

  @Override
  public Expense updateExpense(ExpenseDTO updatedExpense, Long expenseId) {
    User loggedUser = userService.getLoggedUserDetail();
    Category category = categoryRepository.findById(updatedExpense.getCategoryId())
      .orElseThrow(()-> new ResourceNotFound("Category not found with id: "+updatedExpense.getCategoryId()));

    Expense expense = null;

    if(loggedUser.getRole().equals(Role.USER)){
      expense = expenseRepository.findByIdAndUserId(expenseId, loggedUser.getId())
        .orElseThrow(() -> new ResourceNotFound("Expense not found with id: "+expenseId+" and user id: "+loggedUser.getId()));
    }else{
      expense = expenseRepository.findById(expenseId)
        .orElseThrow(()-> new ResourceNotFound("Expense not found with id: "+expenseId));
    }


    String title = !updatedExpense.getTitle().equals(expense.getTitle()) ? updatedExpense.getTitle() : expense.getTitle();
    String description = !updatedExpense.getDescription().equals(expense.getDescription()) ? updatedExpense.getDescription() : expense.getDescription();
    Double amount = !updatedExpense.getAmount().equals(expense.getAmount()) ? updatedExpense.getAmount() : expense.getAmount();
    byte[] receipt = !Arrays.equals(updatedExpense.getReceipt(),expense.getReceipt()) && updatedExpense.getReceipt() != null ? updatedExpense.getReceipt() : expense.getReceipt();
    Category categoryToSet = !updatedExpense.getCategoryId().equals(expense.getId()) ? category : expense.getCategory();


    expense = Expense
      .builder()
      .title(title)
      .description(description)
      .amount(amount)
      .receipt(receipt)
      .category(categoryToSet)
      .build();

    return expenseRepository.save(expense);

  }

  @Override
  public void deleteExpense(Long expenseId) {
    User loggedUser = userService.getLoggedUserDetail();
    Expense expense = null;
    if(loggedUser.getRole().equals(Role.USER)){
      expense = expenseRepository.findByIdAndUserId(expenseId,loggedUser.getId())
        .orElseThrow(()-> new ResourceNotFound("Expense not found with id: "+expenseId+" and user id: "+loggedUser.getId()));
    }else{
      expense = expenseRepository.findById(expenseId)
        .orElseThrow(()->new ResourceNotFound("Expense not found with id: "+expenseId));
    }

    expenseRepository.delete(expense);
  }
}
