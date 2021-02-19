package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.Prontuario;
import com.rd.projetointegrador.rdservicesapi.service.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class ProntuarioController {

    @Autowired ProntuarioService service;

    //Buscar prontuário por Id
    @GetMapping("/prontuario/{id}")
    public ResponseEntity buscarProntuarioId(@PathVariable("id") BigInteger id) {
        try {
            Prontuario p = service.buscarProntuarioId(id);
            return ResponseEntity.status(HttpStatus.OK).body(p);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar prontuário!");
        }
    }

    //Listar todos os prontuários
    @GetMapping("/prontuario")
    public ResponseEntity getProntuarios() {
        try {
            List<Prontuario> prontuarios = service.listarProntuarios();
            return ResponseEntity.status(HttpStatus.OK).body(prontuarios);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar prontuários!");
        }

    }

    //Cadastrar prontuário
    @PostMapping("/prontuario")
    public ResponseEntity cadastrarProntuario(@RequestBody Prontuario prontuario) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarProntuario(prontuario));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar prontuário!");
        }
    }

}

