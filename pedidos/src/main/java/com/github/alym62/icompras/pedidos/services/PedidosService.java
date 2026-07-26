package com.github.alym62.icompras.pedidos.services;

import com.github.alym62.icompras.pedidos.domain.ItemPedidoPersistence;
import com.github.alym62.icompras.pedidos.domain.PedidoPersistence;
import com.github.alym62.icompras.pedidos.domain.enums.StatusPedido;
import com.github.alym62.icompras.pedidos.repositories.PedidosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PedidosService {
    private final PedidosRepository pedidosRepository;
    private final ItemPedidosService itemPedidosService;

    public PedidoPersistence criarPedido(PedidoPersistence pedido) {
        pedido.setStatus(StatusPedido.REALIZADO);
        pedido.setDataPedido(LocalDateTime.now());

        BigDecimal total = calcularTotalDoPedido(pedido.getItens());
        pedido.setTotal(total);

        popularPedido(pedido);

        pedido = pedidosRepository.save(pedido);
        itemPedidosService.salvarTodosItensDoPedido(pedido.getItens());

        return pedido;
    }

    private BigDecimal calcularTotalDoPedido(Set<ItemPedidoPersistence> itensDoPedido) {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemPedidoPersistence item : itensDoPedido) {
            total = item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())).add(total).abs();
        }

        return total;
    }

    private void popularPedido(PedidoPersistence pedido) {
        pedido.getItens().forEach(item -> item.setPedido(pedido));
    }
}
