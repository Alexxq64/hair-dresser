package com.example.hair_dresser.mapper;

import com.example.hair_dresser.dto.FeedbackDto;
import com.example.hair_dresser.entity.Feedback;
import com.example.hair_dresser.entity.Procedure;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class FeedbackMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public FeedbackDto toDto(Feedback feedback) {
        if (feedback == null) return null;

        return FeedbackDto.builder()
                .id(feedback.getId())
                .procedureId(feedback.getProcedure() != null ? feedback.getProcedure().getId() : null)
                .clientId(feedback.getClientId())
                .rating(feedback.getRating())
                .comment(feedback.getComment())
                .createdAt(feedback.getCreatedAt() != null ? feedback.getCreatedAt().format(FORMATTER) : null)
                .updatedAt(feedback.getUpdatedAt() != null ? feedback.getUpdatedAt().format(FORMATTER) : null)
                .build();
    }

    public Feedback toEntity(FeedbackDto dto) {
        if (dto == null) return null;

        return Feedback.builder()
                .procedure(dto.getProcedureId() != null ? Procedure.builder().id(dto.getProcedureId()).build() : null)
                .clientId(dto.getClientId())
                .rating(dto.getRating())
                .comment(dto.getComment())
                .createdAt(LocalDateTime.now()) // будет переопределено @PrePersist
                .build();
    }

    public void updateEntityFromDto(FeedbackDto dto, Feedback feedback) {
        if (dto == null || feedback == null) return;

        feedback.setProcedure(dto.getProcedureId() != null ? Procedure.builder().id(dto.getProcedureId()).build() : null);
        feedback.setClientId(dto.getClientId());
        feedback.setRating(dto.getRating());
        feedback.setComment(dto.getComment());
        // updatedAt устанавливается автоматически через @PreUpdate
    }
}
