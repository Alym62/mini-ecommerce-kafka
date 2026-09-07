package com.github.alym62.icompras.faturamento.service;

import com.github.alym62.icompras.faturamento.bucket.BucketFile;
import com.github.alym62.icompras.faturamento.input.ClienteInput;
import com.github.alym62.icompras.faturamento.input.PedidoInput;
import com.github.alym62.icompras.faturamento.publisher.FaturamentoPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotaFiscalService {
    @Value("classpath:reports/nota-fiscal.jrxml")
    private Resource notaFiscal;
    @Value("classpath:reports/logo.jpg")
    private Resource logo;

    private final BucketService bucketService;
    private final FaturamentoPublisher faturamentoPublisher;

    public void gerarNotaFiscal(PedidoInput pedido) {
        log.info("Nota fiscal sendo gerada para o pedido: {}", pedido.codigo());

        byte[] notaFiscalGerada = this.gerarPDFDaNotaFiscal(pedido);
        if (notaFiscalGerada == null) {
            return;
        }

        String nomeDoArquivo = String.format("nota_fiscal_%d.pdf", pedido.codigo());
        BucketFile arquivo = new BucketFile(
                nomeDoArquivo,
                new ByteArrayInputStream(notaFiscalGerada),
                MediaType.APPLICATION_PDF,
                notaFiscalGerada.length
        );

        String arquivoDeFaturamentoGerado = bucketService.uploadDoArquivoDeFaturamento(arquivo);

        log.info("Nota fiscal gerada: {}", arquivoDeFaturamentoGerado);

        String urlDoArquivo = bucketService.obterArquivoDeFaturamentoComPrazoDeUmaSemana(arquivoDeFaturamentoGerado);
        faturamentoPublisher.publicarPedidoFaturado(pedido.codigo(), urlDoArquivo);
    }

    private byte[] gerarPDFDaNotaFiscal(PedidoInput pedido) {
        try(InputStream in = notaFiscal.getInputStream()) {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("LOGO", logo.getFile().getAbsolutePath());

            this.preencherDadosDoCliente(pedido.cliente(), parametros);
            this.preencherDadosDoPedido(pedido.data(), pedido.total(), parametros);

            JRBeanCollectionDataSource collection = new JRBeanCollectionDataSource(pedido.itens());

            JasperReport jasper = JasperCompileManager.compileReport(in);
            JasperPrint printComOsDados = JasperFillManager.fillReport(jasper, parametros, collection);

            return JasperExportManager.exportReportToPdf(printComOsDados);
        } catch (Exception exception) {
            log.error("Não foi possível gerar o relatório: {}", exception.getMessage());
            return null;
        }
    }

    private void preencherDadosDoCliente(ClienteInput cliente, Map<String, Object> parametros) {
        parametros.put("NOME", cliente.nome());
        parametros.put("CPF", cliente.cpf());
        parametros.put("LOGRADOURO", cliente.logradouro());
        parametros.put("NUMERO", cliente.numero());
        parametros.put("EMAIL", cliente.email());
        parametros.put("TELEFONE", cliente.telefone());
    }

    private void preencherDadosDoPedido(String dataDoPedido, BigDecimal total, Map<String, Object> parametros) {
        parametros.put("DATA_PEDIDO", dataDoPedido);
        parametros.put("TOTAL_PEDIDO", total);
    }
}
