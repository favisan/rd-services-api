package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.ReceituarioInput;
import com.rd.projetointegrador.rdservicesapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class ReceituarioController {

    @Autowired private ReceituarioService receituarioService;

    //BUSCAR RECEITUÁRIO POR ID
    @GetMapping("/receituario/{idReceituario}")
    public ResponseEntity getReceituarioById(@PathVariable("idReceituario") BigInteger idReceituario) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(receituarioService.exibirReceituarioPorId(idReceituario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar receituário.");
        }
    }

    //BUSCAR RECEITUÁRIOS POR ID DE PRONTUADRIO
    @GetMapping("/receituario/prontuario/{idProntuario}")
    public ResponseEntity getReceituarioByIdProntuario(@PathVariable("idProntuario") BigInteger idProntuario) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(receituarioService.listarReceituarioPorIdProntuario(idProntuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar receituários.");
        }
    }

    //EXIBIR LISTAS DA TELA DE RECEITUÁRIO E CAMPOS DESABILITADOS PREENCHIDOS
    @GetMapping("/receituario/{idMedico}/{idPaciente}")
    public ResponseEntity getReceituarios(@PathVariable("idMedico") BigInteger idMedico,@PathVariable("idPaciente") BigInteger idPaciente) {

        return ResponseEntity.status(HttpStatus.OK).body(receituarioService.preencherReceituario(idMedico, idPaciente));

    }

    //CADASTRAR RECEITUÁRIO
    @PostMapping("/receituario")
    public ResponseEntity setReceituario(@RequestBody ReceituarioInput receituario) {

        return ResponseEntity.status(HttpStatus.CREATED).body(receituarioService.inserirReceituario(receituario));

    }
}