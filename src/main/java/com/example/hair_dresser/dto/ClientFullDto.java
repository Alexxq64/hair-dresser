package com.example.hair_dresser.dto;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientFullDto {
    private Long id;
    private String name;
    private int rating;
    private String email;
    private String birthDate;
    private String registeredAt;

    private List<VisitDto> visits;
}
