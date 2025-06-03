package com.example.hair_dresser.repo;

import com.example.hair_dresser.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
