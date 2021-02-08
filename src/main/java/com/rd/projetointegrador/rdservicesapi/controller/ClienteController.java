package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.AreaDoCliente;
import com.rd.projetointegrador.rdservicesapi.dto.FormularioCadastro;
import com.rd.projetointegrador.rdservicesapi.dto.FormularioMeusDados;
import com.rd.projetointegrador.rdservicesapi.dto.InputCliente;
import com.rd.projetointegrador.rdservicesapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.websocket.server.PathParam;
import java.math.BigInteger;

public class ClienteController {
    //GRUPO1

    @Autowired
    private ClienteService service;

    //PÁGINA DE CADASTRO
    @GetMapping("/cliente/cadastro")
    public ResponseEntity getFormularioCadastro(){
        FormularioCadastro formularioCadastro = service.getFormularioCadastro();
        return ResponseEntity.status(HttpStatus.OK).body(formularioCadastro);
    }

    //PÁGINA MEUS-DADOS
    @GetMapping("/cliente/meus-dados/{id}")
    public ResponseEntity getFormularioMeusDados(@PathVariable("idUsuario") BigInteger idUsuario){
        FormularioMeusDados formularioMeusDados = service.getFormularioMeusDados(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(formularioMeusDados);
    }

    //PÁGINA ÁREA CLIENTE
    @GetMapping("/cliente/area-cliente/{id}")
    public ResponseEntity getAreaDoCliente(@PathVariable("idUsuario") BigInteger idUsuario){
        AreaDoCliente areaDoCliente = service.getAreaDoCliente(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(areaDoCliente);
    }


    //POST PÁGINA DE CADASTRO (Novo Usuario + Login + Contato + Cartao + Contrato)
    @PostMapping("/cliente/cadastro")
    public ResponseEntity cadastrarUsuario(@RequestBody InputCliente inputCliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarCliente(inputCliente));
    }
}
