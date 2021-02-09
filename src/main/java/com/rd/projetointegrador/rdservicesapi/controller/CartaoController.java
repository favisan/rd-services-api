package com.rd.projetointegrador.rdservicesapi.controller;
import com.rd.projetointegrador.rdservicesapi.dto.Cartao;
import com.rd.projetointegrador.rdservicesapi.entity.CartaoEntity;

import com.rd.projetointegrador.rdservicesapi.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rd.projetointegrador.rdservicesapi.service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class CartaoController {
    //GRUPO1

    @Autowired
    private CartaoService service;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cartao")
    public ResponseEntity getCartoes() {
        List<CartaoEntity> cartoes = service.getCartoes();
        return ResponseEntity.status(HttpStatus.OK).body(cartoes);
    }

    @GetMapping ("/cartao/{idCartao}")
    public ResponseEntity getCartao(@PathVariable("idCartao") BigInteger idCartao) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getCartao(idCartao));
    }

    //GRUPO2
    @GetMapping("/cartoes/{idUsuario}")
    public ResponseEntity verCartaoByUser(@PathVariable("idUsuario") BigInteger idUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getCartaobyUsuario(usuarioService.getUsuario(idUsuario)));
    }

    @PostMapping("/cartao") //CADASTRO
    public ResponseEntity cadastrarCartao(@RequestBody Cartao cartao) {
        return ResponseEntity.status(HttpStatus.OK).body(service.cadastrarCartao(cartao));
    }

    @PutMapping("/cartao/{idCartao}") //ALTERAÇÕES
    public ResponseEntity alterarCartao(@RequestBody Cartao cartao, @PathVariable("idCartao") BigInteger idCartao) {

        return ResponseEntity.status(HttpStatus.OK).body(service.alterarCartao(cartao, idCartao));
    }
    @DeleteMapping("/cartao/{id}") //DELETAR
    public ResponseEntity excluirCartao(@PathVariable("id") BigInteger idCartao) {
        return ResponseEntity.status(HttpStatus.OK).body(service.excluirCartao(idCartao));
    }

}

