package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.Receituario;
import com.rd.projetointegrador.rdservicesapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class ReceituarioController {

    @Autowired
    private ReceituarioService receituarioService;

    @GetMapping("/receituario/{idReceituario}")
    public ResponseEntity getReceituarioById(@PathVariable("idReceituario") BigInteger idReceituario) {

        return ResponseEntity.status(HttpStatus.OK).body(receituarioService.exibirReceituarioPorId(idReceituario));

    }

    @GetMapping("/receituario/prontuario/{idProntuario}")
    public ResponseEntity getReceituarioByIdProntuario(@PathVariable("idProntuario") BigInteger idProntuario) {

        return ResponseEntity.status(HttpStatus.OK).body(receituarioService.listarReceituarioPorIdProntuario(idProntuario));

    }

    @GetMapping("/receituario/{idMedico}/{idPaciente}")
    public ResponseEntity getReceituarios(@PathVariable("idMedico") BigInteger idMedico,@PathVariable("idPaciente") BigInteger idPaciente) {

        return ResponseEntity.status(HttpStatus.OK).body(receituarioService.preencherReceituario(idMedico, idPaciente));

    }

    @PostMapping("/receituario")
    public ResponseEntity setReceituario(@RequestBody Receituario receituario) {

        return ResponseEntity.status(HttpStatus.CREATED).body(receituarioService.inserirReceituario(receituario));

    }
}