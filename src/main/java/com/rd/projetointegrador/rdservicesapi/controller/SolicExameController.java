package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.SolicExame;
import com.rd.projetointegrador.rdservicesapi.service.SolicExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class SolicExameController {

    @Autowired
    SolicExameService service;


    @PostMapping("/solic_exame")
    public ResponseEntity gravarSolicExame(@RequestBody SolicExame solicExame) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.inserirSolicExame(solicExame));
    }

    @GetMapping("/solic_exame/prontuario/{idProntuario}")
    public ResponseEntity buscarSolicitacaoPorIdProntuario(@PathVariable("idProntuario") BigInteger idProntuario) {

        return ResponseEntity.status(HttpStatus.OK).body(service.listarSolicExamePorIdProntuario(idProntuario));
    }

    @GetMapping("/solic_exame/solicitacao/{idSolicitacao}")
    public ResponseEntity getSolicitacaoPorIdSolicitacao(@PathVariable("idSolicitacao") BigInteger idSolicitacao) {

        return ResponseEntity.status(HttpStatus.OK).body(service.exibirSolicExamePorIdSolicitacao(idSolicitacao));
    }

    @GetMapping("/solic_exame/{idMedico}")
    public ResponseEntity preencherSolicitacaoInicial(@PathVariable("idMedico") BigInteger idMedico) {

        return ResponseEntity.status(HttpStatus.OK).body(service.preencherSolicitacaoInicial(idMedico));

    }


//    @GetMapping("/solic_exame")
//    public ResponseEntity getSolicExames() {
//
//        List<SolicExame> exames = service.listarSolicExame();
//        return ResponseEntity.status(HttpStatus.OK).body(exames);
//    }
}
