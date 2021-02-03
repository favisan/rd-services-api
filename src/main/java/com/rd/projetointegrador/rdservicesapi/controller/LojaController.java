package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.Loja;
import com.rd.projetointegrador.rdservicesapi.service.ContatoService;
import com.rd.projetointegrador.rdservicesapi.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class LojaController {

    @Autowired
    private LojaService service;

    @Autowired
    private ContatoService contatoService;

    @GetMapping("/loja")
    public ResponseEntity getLojas(){
        List<Loja> lojas = service.getLojas();
        return ResponseEntity.ok(lojas);
    }

    @GetMapping("/loja/contato/{idLoja}")
    public ResponseEntity getContatosLoja(@PathVariable("idLoja") BigInteger idLoja) {
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.getContatosLoja(idLoja));
    }

}
