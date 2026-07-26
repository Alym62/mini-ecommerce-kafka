package com.github.alym62.icompras.pedidos.controllers;

import com.github.alym62.icompras.pedidos.controllers.dto.request.NovoPedidoRequestDto;
import com.github.alym62.icompras.pedidos.controllers.dto.response.PedidoResponseDto;
import com.github.alym62.icompras.pedidos.domain.PedidoPersistence;
import com.github.alym62.icompras.pedidos.mappers.PedidosMapper;
import com.github.alym62.icompras.pedidos.services.PedidosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/pedidos")
@RequiredArgsConstructor
public class PedidosController {
    private final PedidosService pedidosService;
    private final PedidosMapper mapper = PedidosMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<PedidoResponseDto> salvar(@RequestBody NovoPedidoRequestDto dto) {
        PedidoPersistence pedidoSalvo = pedidosService.criarPedido(mapper.dtoRequestToPersistence(dto));

        return ResponseEntity.created(URI.create("/api/v1/produtos/detalhes/" + pedidoSalvo.getCodigo())).body(null);
    }

    @GetMapping("/detalhes/{codigo}")
    public ResponseEntity<PedidoResponseDto> obterPedido(@PathVariable Long codigo) {
        return null;
    }
}
