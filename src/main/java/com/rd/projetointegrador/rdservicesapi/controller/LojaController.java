package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.Loja;
import com.rd.projetointegrador.rdservicesapi.entity.ReceitaEntity;
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
    }//Retorna todas as lojas e seus respectivos endereços e contatos

    @GetMapping("/loja/id/{id}")
    public ResponseEntity getLoja(@PathVariable("id") BigInteger id){
        Loja loja = service.getLojaById(id);
        return ResponseEntity.ok(loja);
    }//Retorna todas as lojas e seus respectivos endereços e contato

    @GetMapping("/lojas/{localidade}")
    public ResponseEntity consultarLojasPorLocalidade(@PathVariable("localidade") String localidade){
        List <Loja> lojas = service.getLojasPorLocalidade(localidade);
        return ResponseEntity.status(HttpStatus.OK).body(lojas);
    }


}
