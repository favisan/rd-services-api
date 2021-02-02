package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.Cidade;
import com.rd.projetointegrador.rdservicesapi.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class CidadeController {

    @Autowired
    CidadeService service;

    @GetMapping("/cidade/{id}")
    public ResponseEntity buscarCidadeId(@PathVariable("id") BigInteger id) {
        Cidade c = service.buscarCidadeId(id);
        return ResponseEntity.status(HttpStatus.OK).body(c);
    }

    @GetMapping("/cidade")
    public ResponseEntity getCidades() {
        List<Cidade> cidades = service.listarCidade();
        return ResponseEntity.status(HttpStatus.OK).body(cidades);
    }

    @GetMapping("/cidadeBuscar/{idUf}")
    public ResponseEntity buscarCidadePorUf(@PathVariable("idUf") BigInteger idUf) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarCidadePorUf(idUf));
    }
}
