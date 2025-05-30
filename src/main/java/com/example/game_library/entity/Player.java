package com.example.game_library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {

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
    @Column
    private String email;

    @Past
    @Column(name = "birth_date")
    private String  birthDate;

    @NotNull
    @Column(name = "registered_at")
    private String  registeredAt;

    // Связь 1 Player - N Achievements с каскадом и orphanRemoval
    @OneToMany(
            mappedBy = "player",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Achievement> achievements = new ArrayList<>();
}
