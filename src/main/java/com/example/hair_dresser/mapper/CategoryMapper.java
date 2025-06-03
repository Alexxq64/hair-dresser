package com.example.hair_dresser.mapper;

import com.example.hair_dresser.dto.CategoryDto;
import com.example.hair_dresser.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDto toDto(Category category) {
        if (category == null) return null;

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public Category toEntity(CategoryDto dto) {
        if (dto == null) return null;

        return Category.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public void updateEntityFromDto(CategoryDto dto, Category category) {
        if (dto == null || category == null) return;

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
    }
}
