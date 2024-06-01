package com.peaceandcode.expensemanager.repository;

import com.peaceandcode.expensemanager.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
