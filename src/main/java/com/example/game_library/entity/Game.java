package com.example.game_library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String title;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String genre;

    @NotNull
    @Min(1950)
    @Max(2100)
    @Column(nullable = false)
    private Integer year;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Size(max = 1000)
    @Column(length = 1000)
    private String description;

    // Многие ко многим с Category с каскадом только persist и merge
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "game_category",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    // Методы для удобного управления связью и синхронизации обеих сторон
    public void addCategory(Category category) {
        categories.add(category);
        category.getGames().add(this);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        category.getGames().remove(this);
    }
}
