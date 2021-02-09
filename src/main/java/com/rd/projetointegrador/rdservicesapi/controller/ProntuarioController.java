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
        @Autowired
        ProntuarioService service;

        @GetMapping("/prontuario/{id}")
        public ResponseEntity buscarProntuarioId(@PathVariable("id") BigInteger id) {

            Prontuario p = service.buscarProntuarioId(id);
            return ResponseEntity.status(HttpStatus.OK).body(p);
        }

        @GetMapping("/prontuario")
        public ResponseEntity getProntuario(@RequestBody Prontuario prontuario) {

            List<Prontuario> prontuarios = service.listarProntuarios(prontuario);
            return ResponseEntity.status(HttpStatus.OK).body(prontuarios);

        }

        @PostMapping("/prontuario")
        public ResponseEntity cadastrarProntuario(@RequestBody Prontuario prontuario) {

        if(prontuario.getIdProntuario() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Atributo nome é obrigatório");
        }

            return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarProntuario(prontuario));
        }

    }

