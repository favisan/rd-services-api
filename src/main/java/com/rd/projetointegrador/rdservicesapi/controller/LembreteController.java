package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.Lembrete;
import com.rd.projetointegrador.rdservicesapi.dto.LembreteIntervalo;
import com.rd.projetointegrador.rdservicesapi.entity.LembreteEntity;
import com.rd.projetointegrador.rdservicesapi.service.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.math.BigInteger;
import java.util.List;

@Controller
public class LembreteController {
    //GRUPO1

    @Autowired
    LembreteService service;

    @GetMapping("/lembrete/{idLembrete}") // BUSCA POR ID
    public ResponseEntity getLembrete(@PathVariable("idLembrete") BigInteger idLembrete) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getLembreteDTO(idLembrete));
    }

    @GetMapping("/lembrete") //Busca de todos os Lembretes
    public ResponseEntity getLembretes() {
        List<Lembrete> lembretes = service.getLembretesDTO();
        return ResponseEntity.status(HttpStatus.OK).body(lembretes);
    }

    @GetMapping("/lembrete/user/{idUsuario}") //Busca de lembrete por usuario
    public ResponseEntity getLembretesByData(@PathVariable("idUsuario") BigInteger idUsuario) {
        List<Lembrete> lembretes = service.getLembretesOrderByDataDesc(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(lembretes);
    }

    @GetMapping("/lembrete/user/{idUsuario}/dt-antiga") //Busca de lembrete por usuario
    public ResponseEntity getLembretesByDataInversa(@PathVariable("idUsuario") BigInteger idUsuario) {
        List<Lembrete> lembretes = service.getLembretesOrderByDataAsc(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(lembretes);
    }

    @GetMapping("/lembrete/user/{idUsuario}/dt-criacao") //Busca de lembrete por usuario
    public ResponseEntity getLembretesByDataCriacao(@PathVariable("idUsuario") BigInteger idUsuario) {
        List<Lembrete> lembretes = service.getLembretesOrderByDataCriacao(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(lembretes);
    }




    @PostMapping("/lembrete") //Cadastrar Novo Lembrete
    public ResponseEntity cadastrarLembrete(@RequestBody Lembrete Lembrete) {
        Boolean retorno = service.cadastrarLembrete(Lembrete);
        if (retorno == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar Lembrete.");
        }
            return ResponseEntity.status(HttpStatus.CREATED).body(retorno);

    }

    @PutMapping("/lembrete/{idLembrete}")
    public ResponseEntity alterarLembrete(@RequestBody Lembrete Lembrete, @PathVariable("idLembrete") BigInteger idLembrete){
        String retorno = service.alterarLembrete(Lembrete, idLembrete);
        return ResponseEntity.ok().body(retorno);

    }

    @DeleteMapping("/lembrete/{idLembrete}") //Excluir Lembrete
    public ResponseEntity excluirLembrete(@PathVariable("idLembrete") BigInteger idLembrete) {
        try {
            Boolean retorno = service.excluirLembrete(idLembrete);
            return ResponseEntity.status(HttpStatus.OK).body(retorno);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir Lembrete");
        }
    }



    //LEMBRETE INTERVALO
    @GetMapping("/lembrete/intervalos") //buscar intervalos para formulario
    public ResponseEntity getLembreteIntervalos() {
        List<LembreteIntervalo> lembretesIntervalo = service.getLembreteIntervalos();
        return ResponseEntity.status(HttpStatus.OK).body(lembretesIntervalo);
    }

}
