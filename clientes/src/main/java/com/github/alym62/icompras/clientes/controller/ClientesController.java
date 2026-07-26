package com.github.alym62.icompras.clientes.controller;

import com.github.alym62.icompras.clientes.controller.dto.request.ClienteRequestDto;
import com.github.alym62.icompras.clientes.controller.dto.response.ClienteResponseDto;
import com.github.alym62.icompras.clientes.domain.ClientePersistence;
import com.github.alym62.icompras.clientes.mapper.ClientesMapper;
import com.github.alym62.icompras.clientes.service.ClientesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/clientes")
public class ClientesController {
    private final ClientesService clientesService;

    @PostMapping
    public ResponseEntity<ClienteResponseDto> salvarCliente(@RequestBody ClienteRequestDto dto) {
        ClientePersistence clienteSalvo = clientesService.salvarCliente(ClientesMapper.requestDtoToPersistence(dto));
        ClienteResponseDto clienteMapeado = ClientesMapper.persistenceToResponseDto(clienteSalvo);

        return ResponseEntity.created(URI.create("api/v1/clientes/" + clienteSalvo.getCodigo())).body(clienteMapeado);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ClienteResponseDto> obterCliente(@PathVariable Long codigo) {
        return clientesService.obterClientePorCodigo(codigo)
                .map(cliente -> ResponseEntity.ok().body(ClientesMapper.persistenceToResponseDto(cliente)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
