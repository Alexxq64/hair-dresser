package com.example.game_library.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data  // Lombok добавит геттеры, сеттеры, toString, equals, hashCode
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private int year;
}
