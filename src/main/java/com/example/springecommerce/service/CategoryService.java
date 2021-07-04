package com.example.springecommerce.service;

import com.example.springecommerce.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    public CategoryDTO saveCategory(CategoryDTO categoryDTO);
    public List<CategoryDTO> getCategories();
    public CategoryDTO getCategory(int id);
    public CategoryDTO updateCategory(CategoryDTO categoryDTO);
    public void deleteCategory(int id);
}
