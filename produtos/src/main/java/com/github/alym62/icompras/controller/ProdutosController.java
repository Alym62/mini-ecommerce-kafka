package com.github.alym62.icompras.controller;

import com.github.alym62.icompras.controller.dto.request.ProdutoRequestDto;
import com.github.alym62.icompras.controller.dto.response.ProdutoResponseDto;
import com.github.alym62.icompras.domain.ProdutoPersistence;
import com.github.alym62.icompras.mapper.ProdutosMapper;
import com.github.alym62.icompras.service.ProdutosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutosController {
    private final ProdutosService produtosService;

    @PostMapping
    public ResponseEntity<ProdutoResponseDto> salvarProduto(@RequestBody ProdutoRequestDto dto) {
        ProdutoPersistence produtoSalvo = produtosService.salvarProduto(ProdutosMapper.requestDtoToPersistence(dto));
        ProdutoResponseDto produtoMapeado = ProdutosMapper.persistenceToResponseDto(produtoSalvo);

        return ResponseEntity.created(URI.create("/api/v1/produtos/" + produtoSalvo.getCodigo())).body(produtoMapeado);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ProdutoResponseDto> obterProduto(@PathVariable Long codigo) {
        return produtosService.obterProdutoPorCodigo(codigo)
                .map(produto -> ResponseEntity.ok().body(ProdutosMapper.persistenceToResponseDto(produto)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
