package com.example.game_library.dto;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerFullDto {
    private Long id;
    private String name;
    private int rating;
    private String email;
    private String birthDate;
    private String registeredAt;

    private List<AchievementDto> achievements;
}
