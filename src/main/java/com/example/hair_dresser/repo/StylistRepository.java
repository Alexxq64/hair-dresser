package com.example.hair_dresser.repo;

import com.example.hair_dresser.entity.Stylist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StylistRepository extends JpaRepository<Stylist, Long> {
}
