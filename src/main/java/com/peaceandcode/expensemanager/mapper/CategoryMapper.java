package com.peaceandcode.expensemanager.mapper;

import com.peaceandcode.expensemanager.dto.CategoryDTO;
import com.peaceandcode.expensemanager.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    Category entity(CategoryDTO categoryDTO);
    CategoryDTO dto(Category category);

}
