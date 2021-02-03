package com.rd.projetospring.primeiroprojeto.controller;


import com.rd.projetospring.primeiroprojeto.dto.Loja;
import com.rd.projetospring.primeiroprojeto.dto.Servico;
import com.rd.projetospring.primeiroprojeto.entity.ContatoEntity;
import com.rd.projetospring.primeiroprojeto.entity.LojaEntity;
import com.rd.projetospring.primeiroprojeto.repository.ContatoRepository;
import com.rd.projetospring.primeiroprojeto.repository.LojaRepository;
import com.rd.projetospring.primeiroprojeto.service.ContatoService;
import com.rd.projetospring.primeiroprojeto.service.LojaService;
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
