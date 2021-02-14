package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.Pagamento;
import com.rd.projetointegrador.rdservicesapi.dto.PagamentoCartao;
import com.rd.projetointegrador.rdservicesapi.dto.PagamentoPlano;
import com.rd.projetointegrador.rdservicesapi.entity.PagamentoEntity;
import com.rd.projetointegrador.rdservicesapi.service.CartaoService;
import com.rd.projetointegrador.rdservicesapi.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.math.BigInteger;
import java.util.List;

@RestController
public class PagamentoController {
    //GRUPO1

    @Autowired
    PagamentoService service;

    @Autowired
    CartaoService cartaoService;

    @GetMapping("/pagamento/{idPagamento}") // BUSCA POR ID
    public ResponseEntity getPagamento(@PathVariable("idPagamento") BigInteger idPagamento) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getPagamentoDTO(idPagamento));

    }

    @GetMapping("/pagamento") //Busca de todos os Pagamentos
    public ResponseEntity getPagamentos() {
        List<Pagamento> pagamentos = service.getPagamentosDTO();
        return ResponseEntity.status(HttpStatus.OK).body(pagamentos);
    }

    @PostMapping("/pagamento") //Cadastrar Novo
    public ResponseEntity cadastrarPagamento(@RequestBody Pagamento pagamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarPagamento(pagamento));

    }

    @PutMapping("/pagamento/{idPagamento}") // Alterar
    public ResponseEntity alterarPagamento(@RequestBody Pagamento pagamento, @PathVariable("idPagamento") BigInteger idPagamento){
        String retorno = service.alterarPagamento(pagamento, idPagamento);
        return ResponseEntity.ok().body(retorno);
    }



    @DeleteMapping("/pagamento/{idPagamento}") //Excluir
    public ResponseEntity excluirPagamento(@PathVariable("idPagamento") BigInteger idPagamento) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.excluirPagamento(idPagamento));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir pagamento");
        }
    }

    //Grupo2 - Cadastro de Pagamento de consulta feito com cartão
    @PostMapping("/pagamento/cartao") //Cadastrar Novo PgtoCartao
    public ResponseEntity setPagamentoCartao(@RequestBody PagamentoCartao pagtoCartao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.setPagamentoComCartao(pagtoCartao));
    }

    //Grupo 2 - Cadastro de Pagamento de consulta feito com plano
    @PostMapping("/pagamento/plano") //Cadastrar Novo PgtoPlano
    public ResponseEntity setPagamentoPlano(@RequestBody PagamentoPlano pagtoPlano) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.setPagamentoComPlano(pagtoPlano));
    }

}

