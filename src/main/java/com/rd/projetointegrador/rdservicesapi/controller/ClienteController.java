package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.AreaDoCliente;
import com.rd.projetointegrador.rdservicesapi.dto.FormularioCadastro;
import com.rd.projetointegrador.rdservicesapi.dto.FormularioMeusDados;
import com.rd.projetointegrador.rdservicesapi.dto.InputCliente;
import com.rd.projetointegrador.rdservicesapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.math.BigInteger;

@RestController
public class ClienteController {
    //GRUPO1

    @Autowired
    private ClienteService service;

    //PÁGINA DE CADASTRO
    @CrossOrigin
    @GetMapping("/cliente/cadastro")
    public ResponseEntity getFormularioCadastro(){
        FormularioCadastro formularioCadastro = service.getFormularioCadastro();
        return ResponseEntity.status(HttpStatus.OK).body(formularioCadastro);
    }

    //PÁGINA MEUS-DADOS
    @GetMapping("/cliente/meus-dados/{idUsuario}")
    public ResponseEntity getFormularioMeusDados(@PathVariable("idUsuario") BigInteger idUsuario){
        FormularioMeusDados formularioMeusDados = service.getFormularioMeusDados(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(formularioMeusDados);
    }

    //PÁGINA ÁREA CLIENTE
    @GetMapping("/cliente/area-cliente/{idUsuario}")
    public ResponseEntity getAreaDoCliente(@PathVariable("idUsuario") BigInteger idUsuario){
        AreaDoCliente areaDoCliente = service.getAreaDoCliente(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(areaDoCliente);
    }


    //POST PÁGINA DE CADASTRO (Novo Usuario + Login + Contato + Cartao + Contrato)
    @PostMapping("/cliente/cadastrar")
    public ResponseEntity cadastrarCliente(@RequestBody InputCliente inputCliente) {
        return service.cadastrarCliente(inputCliente);
    }

    @PutMapping("/cliente/alterar/{idUsuario}")
    public ResponseEntity alterarCliente(@PathVariable("idUsuario") BigInteger idUsuario, @RequestBody InputCliente inputCliente) {
        return service.alterarCliente(idUsuario, inputCliente);
    }
}
