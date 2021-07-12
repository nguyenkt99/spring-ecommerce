package com.example.springecommerce.controller;

import com.example.springecommerce.dto.CategoryDTO;
import com.example.springecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public CategoryDTO addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return categoryService.saveCategory(categoryDTO);
    }

    @GetMapping
    public List<CategoryDTO> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public CategoryDTO findCategory(@PathVariable int id) {
        return categoryService.getCategory(id);
    }

    @PutMapping("/{id}")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable int id) {
        categoryDTO.setId(id);
        return categoryService.updateCategory(categoryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
    }


}
