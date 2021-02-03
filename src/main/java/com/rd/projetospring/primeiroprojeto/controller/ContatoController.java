package com.rd.projetospring.primeiroprojeto.controller;

import com.rd.projetospring.primeiroprojeto.dto.Contato;
import com.rd.projetospring.primeiroprojeto.dto.Loja;
import com.rd.projetospring.primeiroprojeto.entity.ContatoEntity;
import com.rd.projetospring.primeiroprojeto.service.ContatoService;
import com.rd.projetospring.primeiroprojeto.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.math.BigInteger;
import java.util.List;

@RestController
public class ContatoController {

  //  @Autowired
 //   private ContatoService service;

//    @GetMapping("/contato/{id}")
//    public ResponseEntity getContatos(@PathVariable("id") BigInteger id){
//        List<ContatoEntity> contatos = service.getContatosLoja(id);
//        return ResponseEntity.ok(contatos);
//    }
}
