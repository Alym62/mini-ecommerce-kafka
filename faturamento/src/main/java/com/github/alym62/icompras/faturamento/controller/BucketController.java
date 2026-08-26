package com.github.alym62.icompras.faturamento.controller;

import com.github.alym62.icompras.faturamento.service.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/bucket")
@RequiredArgsConstructor
public class BucketController {
    private final BucketService service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDoArquivo(@RequestParam("arquivo") MultipartFile arquivo) {
        service.uploadDoArquivoDeFaturamento(arquivo);
        return ResponseEntity.ok().build();
    }
}
