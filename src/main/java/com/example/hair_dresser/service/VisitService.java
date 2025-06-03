package com.example.hair_dresser.service;

import com.example.hair_dresser.dto.VisitDto;
import com.example.hair_dresser.entity.Client;
import com.example.hair_dresser.entity.Procedure;
import com.example.hair_dresser.entity.Visit;
import com.example.hair_dresser.mapper.VisitMapper;
import com.example.hair_dresser.repo.ClientRepository;
import com.example.hair_dresser.repo.ProcedureRepository;
import com.example.hair_dresser.repo.VisitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitService {

    private static final Logger log = LoggerFactory.getLogger(VisitService.class);

    private final VisitRepository visitRepository;
    private final ClientRepository clientRepository;
    private final ProcedureRepository procedureRepository;
    private final VisitMapper visitMapper;

    public VisitService(
            VisitRepository visitRepository,
            ClientRepository clientRepository,
            ProcedureRepository procedureRepository,
            VisitMapper visitMapper
    ) {
        this.visitRepository = visitRepository;
        this.clientRepository = clientRepository;
        this.procedureRepository = procedureRepository;
        this.visitMapper = visitMapper;
    }

    public List<VisitDto> findAllDto() {
        log.info("Fetching all visits");
        List<VisitDto> dtos = visitRepository.findAll()
                .stream()
                .map(visitMapper::toDto)
                .collect(Collectors.toList());
        log.debug("Found {} visits", dtos.size());
        return dtos;
    }

    public VisitDto findByIdDto(Long id) {
        log.info("Fetching visit by id={}", id);
        return visitRepository.findById(id)
                .map(visitMapper::toDto)
                .orElseGet(() -> {
                    log.warn("Visit with id={} not found", id);
                    return null;
                });
    }

    public VisitDto saveDto(VisitDto visitDto) {
        log.info("Saving visit: {}", visitDto);

        Client client = clientRepository.findById(visitDto.getClientId())
                .orElseThrow(() -> {
                    log.error("Client not found with id {}", visitDto.getClientId());
                    return new RuntimeException("Client not found with id " + visitDto.getClientId());
                });

        Procedure procedure = procedureRepository.findById(visitDto.getProcedureId())
                .orElseThrow(() -> {
                    log.error("Procedure not found with id {}", visitDto.getProcedureId());
                    return new RuntimeException("Procedure not found with id " + visitDto.getProcedureId());
                });

        Visit saved = visitRepository.save(
                visitMapper.toEntity(visitDto, client, procedure)
        );

        log.info("Saved visit with id={}", saved.getId());
        return visitMapper.toDto(saved);
    }

    public VisitDto updateDto(Long id, VisitDto visitDto) {
        log.info("Updating visit with id={}", id);

        Client client = clientRepository.findById(visitDto.getClientId())
                .orElseThrow(() -> {
                    log.error("Client not found with id {}", visitDto.getClientId());
                    return new RuntimeException("Client not found with id " + visitDto.getClientId());
                });

        Procedure procedure = procedureRepository.findById(visitDto.getProcedureId())
                .orElseThrow(() -> {
                    log.error("Procedure not found with id {}", visitDto.getProcedureId());
                    return new RuntimeException("Procedure not found with id " + visitDto.getProcedureId());
                });

        return visitRepository.findById(id)
                .map(existing -> {
                    visitMapper.updateEntityFromDto(visitDto, existing, client, procedure);
                    Visit updated = visitRepository.save(existing);
                    log.info("Updated visit with id={}", updated.getId());
                    return visitMapper.toDto(updated);
                })
                .orElseGet(() -> {
                    log.warn("Visit with id={} not found for update", id);
                    return null;
                });
    }

    public void deleteById(Long id) {
        log.info("Deleting visit with id={}", id);
        visitRepository.deleteById(id);
        log.info("Deleted visit with id={}", id);
    }
}
