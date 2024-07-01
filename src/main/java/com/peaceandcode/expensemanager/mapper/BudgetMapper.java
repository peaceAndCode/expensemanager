package com.peaceandcode.expensemanager.mapper;

import com.peaceandcode.expensemanager.dto.BudgetDTO;
import com.peaceandcode.expensemanager.entity.Budget;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BudgetMapper {
  Budget entity(BudgetDTO budgetDTO);
  BudgetDTO dto(Budget budget);
}
