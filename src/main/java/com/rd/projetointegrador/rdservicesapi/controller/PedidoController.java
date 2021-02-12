package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.AgServico;
import com.rd.projetointegrador.rdservicesapi.dto.Cartao;
import com.rd.projetointegrador.rdservicesapi.dto.PedidoInput;
import com.rd.projetointegrador.rdservicesapi.dto.Planos;
import com.rd.projetointegrador.rdservicesapi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping("/pedido")
    public ResponseEntity cadastrarAgendamento(@RequestBody PedidoInput pedidoInput) {
        System.out.println(pedidoInput.getIdUsuario());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarAgendamento(pedidoInput.getAgendamentos(), pedidoInput.getIdUsuario(), pedidoInput.getCartao()));
    }
}
