package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.SolicExame;
import com.rd.projetointegrador.rdservicesapi.service.SolicExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class SolicExameController {

    @Autowired
    SolicExameService service;


    @PostMapping("/solic_exame")
    public ResponseEntity gravarSolicExame(@RequestBody SolicExame solicExame) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarSolicExame(solicExame));

    }

    @GetMapping("/solic_exame")
    public ResponseEntity getSolicExames() {

        List<SolicExame> exames = service.listarSolicExame();
        return ResponseEntity.status(HttpStatus.OK).body(exames);

    }

    @GetMapping("/solic_exame/{idPaciente}")
    public ResponseEntity buscarSolicitacaoPorId(@PathVariable("idPaciente") BigInteger idPaciente) {

        return ResponseEntity.status(HttpStatus.OK).body(service.buscarUsuarioId(idPaciente));

    }
}
