package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.entity.ReceitaEntity;
import com.rd.projetointegrador.rdservicesapi.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.math.BigInteger;
import java.util.List;

@RestController
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping("/receitas/{nomeReceita}")
    public ResponseEntity consultarReceitas(@PathVariable("nomeReceita") String nomeReceita){
        System.out.println("Nome Receita um: " + nomeReceita);
        List <ReceitaEntity> receitas = receitaService.consultarReceitas(nomeReceita);
        return ResponseEntity.status(HttpStatus.OK).body(receitas);
    }

    @GetMapping("/receita/{id}")
        public ResponseEntity consultarReceita(@PathVariable("id") BigInteger id){
        System.out.println("id: " + id);
        ReceitaEntity receita = receitaService.consultarReceita(id);
        return ResponseEntity.status(HttpStatus.OK).body(receita);
    }
}

