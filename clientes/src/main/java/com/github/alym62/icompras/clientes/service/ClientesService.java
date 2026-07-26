package com.github.alym62.icompras.clientes.service;

import com.github.alym62.icompras.clientes.domain.ClientePersistence;
import com.github.alym62.icompras.clientes.repository.ClientesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientesService {
    private final ClientesRepository clientesRepository;

    public ClientePersistence salvarCliente(ClientePersistence persistence) {
        return clientesRepository.save(persistence);
    }

    public Optional<ClientePersistence> obterClientePorCodigo(Long codigo) {
        return clientesRepository.findById(codigo);
    }
}
