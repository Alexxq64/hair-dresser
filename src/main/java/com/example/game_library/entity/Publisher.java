package com.example.game_library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "publishers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Size(max = 255)
    private String address;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String contactEmail;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime foundedAt;

    @Column
    private LocalDateTime updatedAt;
}
