package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.entity.GeneroEntity;
import com.rd.projetointegrador.rdservicesapi.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class GeneroController {

    @Autowired
    GeneroService service;

    @GetMapping("/genero/{idGenero}") // BUSCA POR ID
    public ResponseEntity getGenero(@PathVariable("idGenero") BigInteger idGenero) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getGenero(idGenero));

    }

    @GetMapping("/genero") //Busca de todos os Generos
    public ResponseEntity getGeneros() {
        List<GeneroEntity> generos = service.getGeneros();
        return ResponseEntity.status(HttpStatus.OK).body(generos);
    }

}
