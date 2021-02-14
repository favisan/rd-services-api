package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.Contrato;
import com.rd.projetointegrador.rdservicesapi.entity.ContratoEntity;
import com.rd.projetointegrador.rdservicesapi.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import javax.websocket.server.PathParam;;
import java.util.List;

@RestController
public class ContratoController {
    //GRUPO1

    @Autowired
    ContratoService service;

    @GetMapping("/contrato/{idContrato}") // BUSCA POR ID
    public ResponseEntity getContrato(@PathVariable("idContrato") BigInteger idContrato) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getContrato(idContrato));

    }

    @GetMapping("/contrato") //Busca de todos os Contratos
    public ResponseEntity getContratos(@PathParam("idContrato") BigInteger idContrato) {
        List<ContratoEntity> contratos = service.getContratos(idContrato);
        return ResponseEntity.status(HttpStatus.OK).body(contratos);
    }

    //Grupo2 - Listar os contratos pela id do Usuario
    @GetMapping("/contrato-usuario/{idUsuario}")
    public ResponseEntity getContratoByUsuario(@PathVariable("idUsuario") BigInteger idUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getContratoDTOByUsuario(idUsuario));
    }

//    @PostMapping("/contrato") //Cadastrar Novo Contrato
//    public ResponseEntity cadastrarContrato(@RequestBody Contrato contrato) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarContrato(contrato));
//
//    }

//    @PutMapping("/contrato/{idContrato}") // Alterar Plano
//    public ResponseEntity alterarContrato(@RequestBody Contrato contrato, @PathVariable("idContrato") BigInteger idContrato){
//        String retorno = service.alterarContrato(contrato, idContrato);
//        return ResponseEntity.ok().body(retorno);
//
//    }

    //Confirmar se haverá ou não exclusão do contrato
    @DeleteMapping("/contrato/{idContrato}") //Excluir Contrato
    public ResponseEntity excluirContrato(@PathVariable("idContrato") BigInteger idContrato) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.excluirContrato(idContrato));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir contrato");
        }
    }

}
