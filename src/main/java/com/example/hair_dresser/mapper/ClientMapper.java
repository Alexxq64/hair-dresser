package com.example.hair_dresser.mapper;

import com.example.hair_dresser.dto.ClientDto;
import com.example.hair_dresser.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDto toDto(Client client) {
        if (client == null) return null;

        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .rating(client.getRating())
                .email(client.getEmail())
                .birthDate(client.getBirthDate())
                .registeredAt(client.getRegisteredAt())
                .build();
    }

    public Client toEntity(ClientDto dto) {
        if (dto == null) return null;

        Client client = new Client();
        client.setName(dto.getName());
        client.setRating(dto.getRating());
        client.setEmail(dto.getEmail());
        client.setBirthDate(dto.getBirthDate());
        client.setRegisteredAt(dto.getRegisteredAt());
        return client;
    }

    public void updateEntityFromDto(ClientDto dto, Client client) {
        if (dto == null || client == null) return;

        client.setName(dto.getName());
        client.setRating(dto.getRating());
        client.setEmail(dto.getEmail());
        client.setBirthDate(dto.getBirthDate());
        client.setRegisteredAt(dto.getRegisteredAt());
    }
}
