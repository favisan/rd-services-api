package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.Receituario;
import com.rd.projetointegrador.rdservicesapi.service.ReceituarioService;
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
    public ResponseEntity exibirReceituarioById(@PathVariable("idReceituario") BigInteger idReceituario) {

        return ResponseEntity.status(HttpStatus.OK).body(receituarioService.exibirReceituarioPorId(idReceituario));

    }

    @GetMapping("/receituario/prontuario/{idProntuario}")
    public ResponseEntity exibirReceituarioByProntuario(@PathVariable("idProntuario") BigInteger idProntuario) {

        return ResponseEntity.status(HttpStatus.OK).body(receituarioService.listarReceituarioPorIdProntuario(idProntuario));

    }

    @PostMapping("/receituario")
    public ResponseEntity inserirReceituario(@RequestBody Receituario receituario) {

        return ResponseEntity.status(HttpStatus.CREATED).body(receituarioService.inserirReceituario(receituario));

    }
}