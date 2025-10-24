package io.growtogether.com.user.application.controller;

import io.growtogether.com.finbill.user.domain.model.PaginatedResponse;
import io.growtogether.com.finbill.user.domain.service.ClientService;
import io.growtogether.com.user.application.mapper.ClientDtoMapper;
import io.growtogether.com.user.application.request.ClientRequest;
import io.growtogether.com.user.application.response.ClientResponse;
import lombok.RequiredArgsConstructor;
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
public class ClientController {

    private final ClientService clientService;
    private final ClientDtoMapper clientDtoMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientResponse>> findClients(@RequestParam(defaultValue = "1") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        var response = clientService.findClients(page, size);
        var clientPaginatedResponse = clientDtoMapper.mapToPaginatedClientResponse(response);
        return ResponseEntity.ok()
                .headers(getHttpHeaders(clientPaginatedResponse))
                .contentType(MediaType.APPLICATION_JSON)
                .body(clientPaginatedResponse.content());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<String> createClient(@Validated @RequestBody ClientRequest clientRequest) {
        var client = clientDtoMapper.mapToClient(clientRequest);
        var createdClient = clientService.registerClient(client);

        URI location = fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdClient)
                .toUri();
        return ResponseEntity.created(location)
                .contentType(MediaType.TEXT_HTML)
                .body("Client created Successfully");
    }

    private static HttpHeaders getHttpHeaders(PaginatedResponse<ClientResponse> paginatedProduct) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(paginatedProduct.totalElements()));
        headers.add("X-Total-Pages", String.valueOf(paginatedProduct.totalPages()));
        headers.add("X-Current-Page", String.valueOf(paginatedProduct.currentPage()));
        headers.add("X-Page-Size", String.valueOf(paginatedProduct.pageSize()));
        return headers;
    }
}
