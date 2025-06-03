package com.example.hair_dresser.mapper;

import com.example.hair_dresser.dto.ProcedureDto;
import com.example.hair_dresser.entity.Procedure;
import org.springframework.stereotype.Component;

@Component
public class ProcedureMapper {

    public ProcedureDto toDto(Procedure procedure) {
        if (procedure == null) return null;

        return ProcedureDto.builder()
                .id(procedure.getId())
                .name(procedure.getName())
                .procedureType(procedure.getProcedureType())
                .durationMinutes(procedure.getDurationMinutes())
                .price(procedure.getPrice())
                .description(procedure.getDescription())
                .build();
    }

    public Procedure toEntity(ProcedureDto dto) {
        if (dto == null) return null;

        return Procedure.builder()
                .name(dto.getName())
                .procedureType(dto.getProcedureType())
                .durationMinutes(dto.getDurationMinutes())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .build();
    }

    public void updateEntityFromDto(ProcedureDto dto, Procedure procedure) {
        if (dto == null || procedure == null) return;

        procedure.setName(dto.getName());
        procedure.setProcedureType(dto.getProcedureType());
        procedure.setDurationMinutes(dto.getDurationMinutes());
        procedure.setPrice(dto.getPrice());
        procedure.setDescription(dto.getDescription());
    }
}
