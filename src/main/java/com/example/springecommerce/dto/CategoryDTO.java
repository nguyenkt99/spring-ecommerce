package com.example.springecommerce.dto;

import com.example.springecommerce.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Integer id;
    private String name;
    private String description;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
    }

    // when create
    public Category toEntity() {
        Category category = new Category();
//        if(this.id != null) { // must has id to update
//            category.setId(this.id);
//        }
        category.setName(this.name);
        category.setDescription(this.description);
        return category;
    }

    // when update
    public Category toEntity(Category oldCategory) {
        oldCategory.setName(this.name);
        oldCategory.setDescription(this.description);
        return oldCategory;
    }
}