package com.example.game_library.mapper;

import com.example.game_library.dto.PublisherDto;
import com.example.game_library.entity.Publisher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PublisherMapper {

    public PublisherDto toDto(Publisher publisher) {
        if (publisher == null) return null;

        return PublisherDto.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .address(publisher.getAddress())
                .contactEmail(publisher.getContactEmail())
                .build();
    }

    public Publisher toEntity(PublisherDto dto) {
        if (dto == null) return null;

        return Publisher.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .contactEmail(dto.getContactEmail())
                .foundedAt(LocalDateTime.now())
                .build();
    }

    public void updateEntityFromDto(PublisherDto dto, Publisher publisher) {
        if (dto == null || publisher == null) return;

        publisher.setName(dto.getName());
        publisher.setAddress(dto.getAddress());
        publisher.setContactEmail(dto.getContactEmail());
        publisher.setUpdatedAt(LocalDateTime.now());
    }
}
