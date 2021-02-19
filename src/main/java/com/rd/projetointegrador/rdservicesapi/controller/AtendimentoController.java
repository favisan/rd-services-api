package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.Atendimento;
import com.rd.projetointegrador.rdservicesapi.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class AtendimentoController {

    @Autowired AtendimentoService service;

    //Buscando todos os atendimentos
    @GetMapping("/atendimentos")
    public ResponseEntity getAtendimentos() {
        try {
            List<Atendimento> atendimentos = service.listarAtendimentos();
            return ResponseEntity.status(HttpStatus.OK).body(atendimentos);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar atendimentos!");
        }

    }

    //Buscando atendimento por id
    @GetMapping("/atendimento/{id}")
    public ResponseEntity buscarAtendiementoPorId(@PathVariable("id") BigInteger id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarAtendimentoId(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar atendimento!");
        }
    }

    //Buscando atendimentos por médico
    @GetMapping("atendimento/medico/{id}")
    public ResponseEntity consultarAtendimentoPorIdMedico(@PathVariable("id") BigInteger id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.consultarPorIdMedico(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar atendimentos!");
        }
    }

    //Buscando atendimentos por cpf de paciente
    @GetMapping("/atendimentosPaciente/{cpf}")
    public ResponseEntity getAtendimentosPorCpf(@PathVariable("cpf") String cpf){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.consultarPorCpf(cpf));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar atendimentos.");
        }
    }

    //Preenchendo a tela Atendimento com os dados que são fixos na tela
    @GetMapping("/atendimento/tela/{idAgPaciente}")
    public ResponseEntity preencherTelaAtendimento(@PathVariable("idAgPaciente") BigInteger idAgPaciente) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.preencherAtendimento(idAgPaciente));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar dados!");
        }
    }

    //Cadastrando atendimento
    @PostMapping("/atendimento")
    public ResponseEntity cadastrarAtendimento(@RequestBody Atendimento atendimento) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarAtendimento(atendimento));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar atendimento!");
        }
    }

}