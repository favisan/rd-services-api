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

    @Autowired
    AtendimentoService service;

    @GetMapping("/atendimento/{id}")
    public ResponseEntity buscarAtendiementoId(@PathVariable("id") BigInteger id) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.buscarAtendimentoId(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.OK).body("Erro ao buscar atendimento.");
        }
    }

    @GetMapping("/atendimento")
    public ResponseEntity getAtendimento() {

        List<Atendimento> atendimentos = service.listarAtendimentos();
        return ResponseEntity.status(HttpStatus.OK).body(atendimentos);

    }

    @PostMapping("/atendimento")
    public ResponseEntity cadastrarAtendimento(@RequestBody Atendimento atendimento) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarAtendimento(atendimento));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.OK).body("Erro ao cadastrar atendimento.");
        }
    }

    @GetMapping("/atendimentoUsuario/{cpf}")
    public ResponseEntity getAtendimentoCpf(@PathVariable("cpf") String cpf){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.consultarPorCpf(cpf));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.OK).body("Erro ao buscar atendimento por cpf.");
        }

    }

    @GetMapping("atendimento/medico/{id}")
    public ResponseEntity consultarPorIdMedico(@PathVariable("id") BigInteger id){

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.consultarPorIdMedico(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.OK).body("Erro ao buscar atendimento pelo id do médico.");
        }
    }
}