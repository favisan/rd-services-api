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
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.inserirSolicExame(solicExame));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao gravar solicitação de exame.");
        }
    }

    @GetMapping("/solic_exame/prontuario/{idProntuario}")
    public ResponseEntity buscarSolicitacaoPorIdProntuario(@PathVariable("idProntuario") BigInteger idProntuario) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.listarSolicExamePorIdProntuario(idProntuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar solicitação de exame.");
        }
    }

    @GetMapping("/solic_exame/solicitacao/{idSolicitacao}")
    public ResponseEntity getSolicitacaoPorIdSolicitacao(@PathVariable("idSolicitacao") BigInteger idSolicitacao) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.exibirSolicExamePorIdSolicitacao(idSolicitacao));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar solicitação de exame.");
        }
    }

//    @GetMapping("/solic_exame/{idMedico}")
//    public ResponseEntity preencherSolicitacaoInicial(@PathVariable("idMedico") BigInteger idMedico) {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(service.preencherSolicitacaoInicial(idMedico));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao recuperar informações pré cadastradas no formulário.");
//        }
//    }


//    @GetMapping("/solic_exame")
//    public ResponseEntity getSolicExames() {
//
//        List<SolicExame> exames = service.listarSolicExame();
//        return ResponseEntity.status(HttpStatus.OK).body(exames);
//    }
}
