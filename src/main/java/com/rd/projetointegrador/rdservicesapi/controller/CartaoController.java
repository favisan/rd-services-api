package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.Cartao;

import com.rd.projetointegrador.rdservicesapi.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigInteger;
import java.util.List;

@RestController
public class CartaoController {


    @Autowired
    CartaoService service;

    @GetMapping("/cartao")
    public ResponseEntity verCartoes() {
        return ResponseEntity.status(HttpStatus.OK).body(service.verCartoes());
    }

    @GetMapping("/cartao/{id}")
    public ResponseEntity verCartao(@PathVariable("id") BigInteger id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.verCartao(id));
    }

    
/*
    @PostMapping("/cartao") //CADASTRO
    public ResponseEntity cadastrarContato(@RequestBody Cartao cartao) {
        return ResponseEntity.status(HttpStatus.OK).body(service.cadastrarCartao(cartao));
    }

    @PutMapping("/cartao/{id}") //ALTERAÇÕES
    public ResponseEntity alterarContato(@RequestBody Cartao cartao, @PathVariable("id") BigInteger id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.alterarCartao(cartao, id));
    }

    @DeleteMapping("/cartao/{id}") //DELETAR
    public ResponseEntity excluirCartao(@PathVariable("id") BigInteger id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.excluirCartao(id));
    }
*/

}
