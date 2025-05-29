package com.example.game_library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "achievements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Многие Achievements принадлежат одному Player
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String title;

    @Size(max = 255)
    private String description;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime achievedAt;
}
