package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.Lembrete;
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

    @Autowired
    LembreteService service;

    @GetMapping("/lembrete/{idLembrete}") // BUSCA POR ID
    public ResponseEntity getLembrete(@PathVariable("idLembrete") BigInteger idLembrete) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getLembreteDTO(idLembrete));
   }

    @GetMapping("/lembrete") //Busca de todos os Lembretes
    public ResponseEntity getLembretes(@PathParam("idLembrete") BigInteger idLembrete) {
        List<Lembrete> lembretes = service.getLembretesDTO(idLembrete);
        return ResponseEntity.status(HttpStatus.OK).body(lembretes);
    }

    @PostMapping("/lembrete") //Cadastrar Novo Lembrete
    public ResponseEntity cadastrarLembrete(@RequestBody Lembrete Lembrete) {
        String retorno = service.cadastrarLembrete(Lembrete);
        if (retorno == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar Lembrete.");
        }
            return ResponseEntity.status(HttpStatus.CREATED).body(retorno);

    }

    @PutMapping("/lembrete/{idLembrete}") // Alterar Plano
    public ResponseEntity alterarLembrete(@RequestBody Lembrete Lembrete, @PathVariable("idLembrete") BigInteger idLembrete){
        String retorno = service.alterarLembrete(Lembrete, idLembrete);
        return ResponseEntity.ok().body(retorno);

    }

    //Confirmar se haverá ou não exclusão do Lembrete
    @DeleteMapping("/lembrete/{idLembrete}") //Excluir Lembrete
    public ResponseEntity excluirLembrete(@PathVariable("idLembrete") BigInteger idLembrete) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.excluirLembrete(idLembrete));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir Lembrete");
        }
    }

}
