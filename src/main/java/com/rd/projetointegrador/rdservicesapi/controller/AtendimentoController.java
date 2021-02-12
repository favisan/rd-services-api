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

        Atendimento a = service.buscarAtendimentoId(id);
        return ResponseEntity.status(HttpStatus.OK).body(a);
    }

    @GetMapping("/atendimento")
    public ResponseEntity getAtendimento() {

        List<Atendimento> atendimentos = service.listarAtendimentos();
        return ResponseEntity.status(HttpStatus.OK).body(atendimentos);

    }

    @PostMapping("/atendimento")
    public ResponseEntity cadastrarAtendimento(@RequestBody Atendimento atendimento) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarAtendimento(atendimento));
    }

    @GetMapping("/atendimentoUsuario/{cpf}")
    public ResponseEntity getAtendimento(@PathVariable("cpf") String cpf){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.consultarPorCpf(cpf));
    }

    @GetMapping("atendimento/medico/{id}")
    public ResponseEntity consultarPorIdMedico(@PathVariable("id") BigInteger id){
        return ResponseEntity.status(HttpStatus.OK).body(service.consultarPorIdMedico(id));
    }
}