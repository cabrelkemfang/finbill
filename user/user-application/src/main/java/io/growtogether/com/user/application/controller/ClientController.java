package io.growtogether.com.user.application.controller;

import io.growtogether.com.finbill.user.domain.model.PaginatedResponse;
import io.growtogether.com.finbill.user.domain.service.ClientService;
import io.growtogether.com.user.application.mapper.ClientDtoMapper;
import io.growtogether.com.user.application.request.ClientRequest;
import io.growtogether.com.user.application.response.ClientResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;
    private final ClientDtoMapper clientDtoMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<ClientResponse>> findClients(@RequestParam(defaultValue = "1") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        log.info("Fetching clients - page: {}, size: {}", page, size);
        var paginatedResponse = clientService.findClients(page, size);
        var clientPaginatedResponse = clientDtoMapper.mapToPaginatedClientResponse(paginatedResponse);
        return ResponseEntity.ok()
                .headers(buildPaginatedResponse(clientPaginatedResponse))
                .contentType(MediaType.APPLICATION_JSON)
                .body(clientPaginatedResponse.content());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createClient(@Validated @RequestBody ClientRequest clientRequest) {
        log.info("Creating new client with email: {}", clientRequest.getEmail());
        var client = clientDtoMapper.mapToClient(clientRequest);
        var createdClient = clientService.registerClient(client);

        URI location = fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdClient)
                .toUri();
        return ResponseEntity.created(location)
                .body("Client created successfully");
    }

    private HttpHeaders buildPaginatedResponse(PaginatedResponse<ClientResponse> paginatedProduct) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(paginatedProduct.totalElements()));
        headers.add("X-Total-Pages", String.valueOf(paginatedProduct.totalPages()));
        headers.add("X-Current-Page", String.valueOf(paginatedProduct.currentPage()));
        headers.add("X-Page-Size", String.valueOf(paginatedProduct.pageSize()));
        return headers;
    }
}
