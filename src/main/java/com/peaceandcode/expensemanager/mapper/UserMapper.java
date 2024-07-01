package com.peaceandcode.expensemanager.mapper;

import com.peaceandcode.expensemanager.dto.UserRegisterDTO;
import com.peaceandcode.expensemanager.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User entity(UserRegisterDTO userRegisterDTO);
}
