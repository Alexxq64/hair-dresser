package com.example.hair_dresser.mapper;

import com.example.hair_dresser.dto.StylistDto;
import com.example.hair_dresser.entity.Stylist;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class StylistMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public StylistDto toDto(Stylist stylist) {
        if (stylist == null) return null;

        return StylistDto.builder()
                .id(stylist.getId())
                .name(stylist.getName())
                .address(stylist.getAddress())
                .contactEmail(stylist.getContactEmail())
                .hireDate(stylist.getHireDate() != null ? stylist.getHireDate().format(FORMATTER) : null)
                .salary(stylist.getSalary())
                .build();
    }

    public Stylist toEntity(StylistDto dto) {
        if (dto == null) return null;

        return Stylist.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .contactEmail(dto.getContactEmail())
                .hireDate(dto.getHireDate() != null ? LocalDateTime.parse(dto.getHireDate(), FORMATTER) : LocalDateTime.now())
                .salary(dto.getSalary())
                .build();
    }

    public void updateEntityFromDto(StylistDto dto, Stylist stylist) {
        if (dto == null || stylist == null) return;

        stylist.setName(dto.getName());
        stylist.setAddress(dto.getAddress());
        stylist.setContactEmail(dto.getContactEmail());

        if (dto.getHireDate() != null) {
            stylist.setHireDate(LocalDateTime.parse(dto.getHireDate(), FORMATTER));
        }

        stylist.setSalary(dto.getSalary());
    }
}
