package com.example.hair_dresser.repo;

import com.example.hair_dresser.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
