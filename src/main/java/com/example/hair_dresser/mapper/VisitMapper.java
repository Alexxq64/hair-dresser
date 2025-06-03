package com.example.hair_dresser.mapper;

import com.example.hair_dresser.dto.VisitDto;
import com.example.hair_dresser.entity.Client;
import com.example.hair_dresser.entity.Procedure;
import com.example.hair_dresser.entity.Visit;
import org.springframework.stereotype.Component;

@Component
public class VisitMapper {

    public VisitDto toDto(Visit visit) {
        if (visit == null) return null;

        return VisitDto.builder()
                .id(visit.getId())
                .clientId(visit.getClient().getId())
                .procedureId(visit.getProcedure().getId())
                .notes(visit.getNotes())
                .visitDateTime(visit.getVisitDateTime())
                .build();
    }

    public Visit toEntity(VisitDto dto, Client client, Procedure procedure) {
        if (dto == null || client == null || procedure == null) return null;

        return Visit.builder()
                .client(client)
                .procedure(procedure)
                .notes(dto.getNotes())
                .visitDateTime(dto.getVisitDateTime())
                .build();
    }

    public void updateEntityFromDto(VisitDto dto, Visit visit, Client client, Procedure procedure) {
        if (dto == null || visit == null || client == null || procedure == null) return;

        visit.setClient(client);
        visit.setProcedure(procedure);
        visit.setNotes(dto.getNotes());
        visit.setVisitDateTime(dto.getVisitDateTime());
    }
}
