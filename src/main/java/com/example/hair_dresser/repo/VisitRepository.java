package com.example.hair_dresser.repo;

import com.example.hair_dresser.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
