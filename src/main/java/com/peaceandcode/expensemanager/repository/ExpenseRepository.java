package com.peaceandcode.expensemanager.repository;

import com.peaceandcode.expensemanager.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
  Page<Expense> findByUserIdOrderByCreatedAtDescCategoryAsc(Long userId, Pageable pageable);
  Page<Expense> findByOrderByCreatedAtDescCategoryAsc(Pageable pageable);

  Page<Expense> findByUserIdOrderByCreatedAtAscCategoryAsc(Long userId, Pageable pageable);
  Page<Expense> findByOrderByCreatedAtAscCategoryAsc(Pageable pageable);

  Page<Expense> findByUserIdOrderByCreatedAtDescCategoryAscAmountDesc(Long userId, Pageable pageable);
  Page<Expense> findByOrderByCreatedAtDescCategoryAscAmountDesc(Pageable pageable);

  Page<Expense> findByUserIdOrderByCreatedAtDescCategoryAscAmountAsc(Long userId, Pageable pageable);
  Page<Expense> findByOrderByCreatedAtDescCategoryAscAmountAsc(Pageable pageable);

  Page<Expense> findByUserIdOrderByCreatedAtAscCategoryAscAmountAsc(Long userId, Pageable pageable);
  Page<Expense> findByOrderByCreatedAtAscCategoryAscAmountAsc(Pageable pageable);

  Page<Expense> findByUserIdOrderByCreatedAtAscCategoryAscAmountDesc(Long userId, Pageable pageable);
  Page<Expense> findByOrderByCreatedAtAscCategoryAscAmountDesc(Pageable pageable);

  //Category methods
  Page<Expense> findByUserIdAndCategoryIdOrderByCreatedAtDescCategoryAsc(Long userId, Pageable pageable);
  Page<Expense> findByCategoryIdOrderByCreatedAtDescCategoryAsc(Pageable pageable);

  Page<Expense> findByUserIdAndCategoryIdOrderByCreatedAtAscCategoryAsc(Long userId, Pageable pageable);
  Page<Expense> findByCategoryIdOrderByCreatedAtAscCategoryAsc(Pageable pageable);

  Page<Expense> findByUserIdAndCategoryIdOrderByCreatedAtDescCategoryAscAmountDesc(Long userId, Pageable pageable);
  Page<Expense> findByCategoryIdOrderByCreatedAtDescCategoryAscAmountDesc(Pageable pageable);

  Page<Expense> findByUserIdAndCategoryIdOrderByCreatedAtDescCategoryAscAmountAsc(Long userId, Pageable pageable);
  Page<Expense> findByCategoryIdOrderByCreatedAtDescCategoryAscAmountAsc(Pageable pageable);

  Page<Expense> findByUserIdAndCategoryIdOrderByCreatedAtAscCategoryAscAmountAsc(Long userId, Pageable pageable);
  Page<Expense> findByCategoryIdOrderByCreatedAtAscCategoryAscAmountAsc(Pageable pageable);

  Page<Expense> findByUserIdAndCategoryIdOrderByCreatedAtAscCategoryAscAmountDesc(Long userId, Pageable pageable);
  Page<Expense> findByCategoryIdOrderByCreatedAtAscCategoryAscAmountDesc(Pageable pageable);

  Optional<Expense> findByIdAndUserId(Long id, Long userId);
}
