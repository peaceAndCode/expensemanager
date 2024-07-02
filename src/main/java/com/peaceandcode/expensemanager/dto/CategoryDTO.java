package com.peaceandcode.expensemanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CategoryDTO {
    @NotNull(message = "Category Id can't be null")
    private Long id;
    @NotBlank(message = "Category name can't be null")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Invalid category name")
    private String name;
}
