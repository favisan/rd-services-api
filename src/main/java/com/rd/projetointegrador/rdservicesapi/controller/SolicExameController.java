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

    @Autowired SolicExameService service;

    //Cadastrar solicitação de exames
    @PostMapping("/solic_exame")
    public ResponseEntity gravarSolicExame(@RequestBody SolicExame solicExame) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.inserirSolicExame(solicExame));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao gravar solicitação de exame.");
        }
    }

    //Buscar solicitação de exames por id de prontuário
    @GetMapping("/solic_exame/prontuario/{idProntuario}")
    public ResponseEntity buscarSolicitacaoPorIdProntuario(@PathVariable("idProntuario") BigInteger idProntuario) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.listarSolicExamePorIdProntuario(idProntuario));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar solicitação de exame.");
        }
    }

    //Buscar solicitação de exames por id
    @GetMapping("/solic_exame/solicitacao/{idSolicitacao}")
    public ResponseEntity getSolicitacaoPorIdSolicitacao(@PathVariable("idSolicitacao") BigInteger idSolicitacao) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.exibirSolicExamePorIdSolicitacao(idSolicitacao));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar solicitação de exame.");
        }
    }

    //Preencher tela de solicitação de exames
    @GetMapping("/solic_exame/{idMedico}/{idPaciente}/{idAgPaciente}")
    public ResponseEntity preencherSolicitacaoInicial(@PathVariable("idMedico") BigInteger idMedico, @PathVariable BigInteger idPaciente, @PathVariable BigInteger idAgPaciente) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.preencherSolicitacaoInicial(idMedico, idPaciente, idAgPaciente));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao recuperar informações pré cadastradas no formulário.");
        }
    }

    //Listar todas as solicitações de exames
    @GetMapping("/solic_exame")
    public ResponseEntity getSolicExames() {
        try {
        List<SolicExame> exames = service.listarSolicExame();
        return ResponseEntity.status(HttpStatus.OK).body(exames);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar solicitações de exames.");
        }
    }
}