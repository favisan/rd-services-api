package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.service.CartaoService;
import com.rd.projetointegrador.rdservicesapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class CartaoController {
    @Autowired
    private CartaoService service;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cartoes/{idUsuario}")
    public ResponseEntity verCartaoByUser(@PathVariable("idUsuario") BigInteger idUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getCartaobyUsuario(usuarioService.getUsuario(idUsuario)));
    }
}
