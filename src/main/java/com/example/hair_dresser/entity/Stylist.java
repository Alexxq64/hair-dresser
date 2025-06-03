package com.example.hair_dresser.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stylists")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stylist {

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
    private LocalDateTime hireDate; // дата найма

    @Min(0)
    @Column
    private Long salary; // зарплата в рублях
}
