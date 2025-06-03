package com.example.hair_dresser.controller;

import com.example.hair_dresser.dto.ClientDto;
import com.example.hair_dresser.dto.ClientFullDto;
import com.example.hair_dresser.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDto> getAll() {
        return clientService.findAllDto();
    }

    @GetMapping("/{id}")
    public ClientDto getById(@PathVariable Long id) {
        return clientService.findByIdDto(id);
    }

    @PostMapping
    public ClientDto create(@Valid @RequestBody ClientDto clientDto) {
        return clientService.saveDto(clientDto);
    }

    @PutMapping("/{id}")
    public ClientDto update(@PathVariable Long id, @Valid @RequestBody ClientDto clientDto) {
        return clientService.updateDto(id, clientDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clientService.deleteById(id);
    }

    @Operation(summary = "Получить полную информацию об клиенте")
    @GetMapping("/{id}/full")
    public ResponseEntity<ClientFullDto> getFullInfo(@PathVariable Long id) {
        ClientFullDto fullDto = clientService.getFullClientInfo(id);
        return ResponseEntity.ok(fullDto);
    }
}
