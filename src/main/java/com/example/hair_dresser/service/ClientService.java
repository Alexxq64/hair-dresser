package com.example.hair_dresser.service;

import com.example.hair_dresser.dto.VisitDto;
import com.example.hair_dresser.dto.ClientDto;
import com.example.hair_dresser.dto.ClientFullDto;
import com.example.hair_dresser.entity.Client;
import com.example.hair_dresser.mapper.VisitMapper;
import com.example.hair_dresser.mapper.ProcedureMapper;
import com.example.hair_dresser.mapper.ClientMapper;
import com.example.hair_dresser.repo.ProcedureRepository;
import com.example.hair_dresser.repo.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final VisitMapper visitMapper;
    private final ProcedureMapper procedureMapper;
    private final ProcedureRepository procedureRepository;

    public ClientService(
            ClientRepository clientRepository,
            ClientMapper clientMapper,
            VisitMapper visitMapper,
            ProcedureMapper procedureMapper,
            ProcedureRepository procedureRepository
    ) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.visitMapper = visitMapper;
        this.procedureMapper = procedureMapper;
        this.procedureRepository = procedureRepository;
    }

    public List<ClientDto> findAllDto() {
        log.info("Fetching all clients");
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    public ClientDto findByIdDto(Long id) {
        log.info("Fetching client by id={}", id);
        return clientRepository.findById(id)
                .map(clientMapper::toDto)
                .orElseGet(() -> {
                    log.warn("Client with id={} not found", id);
                    return null;
                });
    }

    public ClientDto saveDto(ClientDto clientDto) {
        log.info("Saving client: {}", clientDto);
        Client saved = clientRepository.save(clientMapper.toEntity(clientDto));
        log.info("Saved client with id={}", saved.getId());
        return clientMapper.toDto(saved);
    }

    public ClientDto updateDto(Long id, ClientDto clientDto) {
        log.info("Updating client with id={}", id);
        return clientRepository.findById(id)
                .map(existing -> {
                    clientMapper.updateEntityFromDto(clientDto, existing);
                    Client updated = clientRepository.save(existing);
                    log.info("Updated client with id={}", updated.getId());
                    return clientMapper.toDto(updated);
                })
                .orElseGet(() -> {
                    log.warn("Client with id={} not found for update", id);
                    return null;
                });
    }

    public void deleteById(Long id) {
        log.info("Deleting client with id={}", id);
        clientRepository.deleteById(id);
        log.info("Deleted client with id={}", id);
    }

    // Методы работы с сущностями напрямую (если нужно)
    public List<Client> findAll() {
        log.info("Fetching all client entities");
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        log.info("Fetching client entity by id={}", id);
        return clientRepository.findById(id).orElse(null);
    }

    public Client save(Client client) {
        log.info("Saving client entity: {}", client);
        Client saved = clientRepository.save(client);
        log.info("Saved client entity with id={}", saved.getId());
        return saved;
    }

    // Составной метод: клиент + достижения (без процедур)
    public ClientFullDto getFullClientInfo(Long clientId) {
        log.info("Fetching full info (without procedures) for client id={}", clientId);

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Игрок не найден"));

        List<VisitDto> visits = client.getVisits().stream()
                .map(visitMapper::toDto)
                .toList();

        return ClientFullDto.builder()
                .id(client.getId())
                .name(client.getName())
                .rating(client.getRating())
                .email(client.getEmail())
                .birthDate(client.getBirthDate().toString())
                .registeredAt(client.getRegisteredAt().toString())
                .visits(visits)
                .build();
    }

}
