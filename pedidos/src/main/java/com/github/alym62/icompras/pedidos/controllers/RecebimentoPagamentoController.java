package com.github.alym62.icompras.pedidos.controllers;

import com.github.alym62.icompras.pedidos.controllers.dto.request.RecebimentoPagamentoRequestDto;
import com.github.alym62.icompras.pedidos.services.RecebimentoPagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/callback-pagamentos/webhook")
@RequiredArgsConstructor
public class RecebimentoPagamentoController {
    private final RecebimentoPagamentoService recebimentoPagamentoService;

    // @TODO: implementar um filtro para a ApiKey
    @PostMapping
    public ResponseEntity<Void> atualizarStatusDePagamento(@RequestBody RecebimentoPagamentoRequestDto dto,
                                                        @RequestHeader String apiKey) {
        recebimentoPagamentoService.atualizarPagamento(dto.codigo(), dto.chavePagamento(), dto.status(), dto.observacoes());
        return ResponseEntity.ok().build();
    }
}
