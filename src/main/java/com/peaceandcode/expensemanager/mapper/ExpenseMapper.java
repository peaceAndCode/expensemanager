package com.peaceandcode.expensemanager.mapper;
import com.peaceandcode.expensemanager.dto.ExpenseDTO;
import com.peaceandcode.expensemanager.entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpenseMapper {
  Expense entity(ExpenseDTO expenseDTO);
  ExpenseDTO dto(Expense expense);
}
