package com.example.hair_dresser.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "procedures")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String name;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String procedureType;

    @NotNull
    @Min(0)
    @Max(1440)
    @Column(nullable = false)
    private Integer durationMinutes;

    @NotNull
    @Column(nullable = false)
    private Long price;

    @Size(max = 1000)
    @Column(length = 1000)
    private String description;

    // Обратная связь с Visit (один ко многим)
    @OneToMany(mappedBy = "procedure", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Visit> visits = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "procedure_category",
            joinColumns = @JoinColumn(name = "procedureId"),
            inverseJoinColumns = @JoinColumn(name = "categoryId")
    )
    private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category) {
        categories.add(category);
        category.getProcedures().add(this);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        category.getProcedures().remove(this);
    }

    @PrePersist
    public void prePersist() {
        if (price == null || price == 0) {
            price = 1000L;
        }
    }
}
