package com.github.alym62.icompras.pedidos.services;

import com.github.alym62.icompras.pedidos.domain.ItemPedidoPersistence;
import com.github.alym62.icompras.pedidos.repositories.ItemPedidosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ItemPedidosService {
    private final ItemPedidosRepository itemPedidosRepository;

    public void salvarTodosItensDoPedido(Set<ItemPedidoPersistence> itens) {
        itemPedidosRepository.saveAll(itens);
    }
}
