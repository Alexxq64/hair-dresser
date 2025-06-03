package com.example.hair_dresser.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Size(max = 255)
    private String description;

    @NotNull
    @Column(nullable = false)
    private Integer serviceCount;

    @Column
    private Integer popularityScore;

    // Обратная сторона связи многие ко многим с Procedure
    @ManyToMany(mappedBy = "categories")
    private Set<Procedure> procedures = new HashSet<>();
}
