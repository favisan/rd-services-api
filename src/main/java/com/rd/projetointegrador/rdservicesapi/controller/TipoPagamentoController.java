package com.rd.projetointegrador.rdservicesapi.controller;


import com.rd.projetointegrador.rdservicesapi.dto.TipoPagamento;
import com.rd.projetointegrador.rdservicesapi.entity.TipoPagamentoEntity;
import com.rd.projetointegrador.rdservicesapi.service.TipoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.math.BigInteger;
import java.util.List;

@RestController
public class TipoPagamentoController {
    //GRUPO1

    @Autowired
    TipoPagamentoService service;

    @GetMapping("/tipoPagamento/{idFormaPagamento}") // BUSCA POR ID
    public ResponseEntity getTipoPagamento(@PathVariable("idFormaPagamento") BigInteger idFormaPagamento){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getTipoPagamento(idFormaPagamento));

    }
    @GetMapping("/tipoPagamento") //Busca de todos os Tipos
    public ResponseEntity getTiposPagamentos() {
        List<TipoPagamentoEntity> tiposPagamentos = service.getTiposPagamentos();
        return ResponseEntity.status(HttpStatus.OK).body(tiposPagamentos);
    }
    @PostMapping("/tipoPagamento") //Cadastrar Novo Tipo
    public ResponseEntity cadastrarTipoPagamento(@RequestBody TipoPagamento tipoPagamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarTipoPagamento(tipoPagamento));
    }

    @PutMapping("/tipoPagamento/{idFormaPagamento}") // Alterar
    public ResponseEntity alterarTipoPagamento(@RequestBody TipoPagamento tipoPagamento, @PathVariable("idFormaPagamento") BigInteger idFormaPagamento){
        String retorno = service.alterarTipoPagamento(tipoPagamento, idFormaPagamento);
        return ResponseEntity.ok().body(retorno);

    }

    @DeleteMapping("/tipoPagamento/{idFormaPagamento}") //Excluir
    public ResponseEntity excluirTipoPagamento(@PathVariable("idFormaPagamento") BigInteger idFormaPagamento) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.excluirTipoPagamento(idFormaPagamento));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir.");
        }
    }
}
