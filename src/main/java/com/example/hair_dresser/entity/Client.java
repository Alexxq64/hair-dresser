package com.example.hair_dresser.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "clients")  // 🔧 ВАЖНО: добавляем явно имя таблицы
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Min(0)
    @Max(5000)
    private int rating;

    @Email
    @Size(max = 100)
    @Column
    private String email;

    @NotBlank
    @Column(name = "birth_date")
    private String birthDate;

    @NotNull
    @Column(name = "registered_at")
    private String registeredAt;

    // Связь 1 Client - N Visits
    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Visit> visits = new ArrayList<>();
}
