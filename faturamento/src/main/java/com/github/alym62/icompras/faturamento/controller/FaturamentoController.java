package com.github.alym62.icompras.faturamento.controller;

import com.github.alym62.icompras.faturamento.service.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/faturamentos")
@RequiredArgsConstructor
public class FaturamentoController {
    private final BucketService bucketService;

    @GetMapping
    public ResponseEntity<String> obterUrlDoArquivo(@RequestParam("nomeDoArquivo") String nomeDoArquivo) {
        String arquivo = bucketService.obterArquivoDeFaturamento(nomeDoArquivo);
        return ResponseEntity.ok(arquivo);
    }
}
