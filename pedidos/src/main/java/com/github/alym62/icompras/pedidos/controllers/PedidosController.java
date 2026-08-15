package com.github.alym62.icompras.pedidos.controllers;

import com.github.alym62.icompras.pedidos.controllers.dto.request.NovoPagamentoRequestDto;
import com.github.alym62.icompras.pedidos.controllers.dto.request.NovoPedidoRequestDto;
import com.github.alym62.icompras.pedidos.controllers.dto.response.PedidoResponseDto;
import com.github.alym62.icompras.pedidos.domain.PedidoPersistence;
import com.github.alym62.icompras.pedidos.mappers.DetalhePedidoMapper;
import com.github.alym62.icompras.pedidos.mappers.PedidosMapper;
import com.github.alym62.icompras.pedidos.controllers.dto.response.DetalhePedidoResponseDto;
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
    private final DetalhePedidoMapper detalhePedidoMapper = DetalhePedidoMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<PedidoResponseDto> salvar(@RequestBody NovoPedidoRequestDto dto) {
        PedidoPersistence pedidoSalvo = pedidosService.criarPedido(mapper.dtoRequestToPersistence(dto));
        PedidoResponseDto pedidoMapeado = mapper.persistenceToDtoResponse(pedidoSalvo);

        return ResponseEntity.created(URI.create("/api/v1/produtos/detalhes/" + pedidoSalvo.getCodigo())).body(pedidoMapeado);
    }

    @PostMapping("/gerar-novo-pagamento/{codigoDoPedido}")
    public ResponseEntity<Void> gerarNovoPagamentoParaPedido(@PathVariable Long codigoDoPedido, @RequestBody NovoPagamentoRequestDto dto) {
        pedidosService.adicionarNovoPagamentoParaPedido(codigoDoPedido, dto.dadosDePagamento(), dto.tipoDePagamento());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/detalhes/{codigo}")
    public ResponseEntity<DetalhePedidoResponseDto> obterDetalhesDoPedido(@PathVariable Long codigo) {
        PedidoPersistence pedido = pedidosService.obterPedidoComDadosCompletos(codigo);
        return ResponseEntity.ok().body(detalhePedidoMapper.persistenceToRepresentationPub(pedido));
    }
}
