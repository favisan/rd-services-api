package com.rd.projetospring.primeiroprojeto.controller;

import com.rd.projetospring.primeiroprojeto.dto.Servico;
import com.rd.projetospring.primeiroprojeto.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.math.BigInteger;
import java.util.List;

@RestController
public class ServicoController {

    @Autowired
    private ServicoService service;

    @GetMapping("/servico/{id}")
    public ResponseEntity getServico(@PathVariable("id") BigInteger id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getServico(id) );
    }

    @GetMapping("/servico")
    public ResponseEntity getServicos(){
        List<Servico> servicos = service.getServicos();
        return ResponseEntity.ok(servicos);
    }

    @PostMapping("/servico")
    public ResponseEntity cadastrarServico(@RequestBody Servico servico){
        if (servico.getNome() == null || servico.getPreco() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome e preço são atributos obrigatórios");
        String retorno = service.cadastrar(servico);
        return ResponseEntity.status(HttpStatus.OK).body(retorno);
    }

    @PutMapping("/servico/{id}")
    public ResponseEntity alterar(@RequestBody Servico servico, @PathVariable("id") BigInteger id){
        String retorno = service.alterar(servico, id);
        return ResponseEntity.ok().body(retorno);
    }

    @DeleteMapping("/servico/{id}")
    public ResponseEntity excluir(@PathVariable("id") BigInteger id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.excluir(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir usuário");
        }
    }
}
