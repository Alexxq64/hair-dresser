package com.example.game_library.mapper;

import com.example.game_library.dto.CategoryDto;
import com.example.game_library.entity.Category;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
                .createdAt(LocalDateTime.now())
                .build();
    }

    public void updateEntityFromDto(CategoryDto dto, Category category) {
        if (dto == null || category == null) return;

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setUpdatedAt(LocalDateTime.now());
    }
}
