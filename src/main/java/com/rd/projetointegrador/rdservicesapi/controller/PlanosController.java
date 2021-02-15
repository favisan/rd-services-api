package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.Planos;
import com.rd.projetointegrador.rdservicesapi.entity.PlanosEntity;
import com.rd.projetointegrador.rdservicesapi.service.PlanosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import java.math.BigInteger;
import java.util.List;

@RestController
public class PlanosController {
    //GRUPO1

    @Autowired PlanosService service;

    @GetMapping("/planos/{idPlano}") // BUSCA POR ID
    public ResponseEntity getPlano(@PathVariable("idPlano") BigInteger idPlano) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getPlano(idPlano));

    }

    @GetMapping("/contrato/user/{idUsuario}")
    public ResponseEntity verContratoByUser(@PathVariable("idUsuario") BigInteger idUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPlanobyUsuario(idUsuario));
    }

    @GetMapping("/planos") //Busca de todos os Planos
    public ResponseEntity getPlanos() {
        List<PlanosEntity> planos = service.getPlanos();
        return ResponseEntity.status(HttpStatus.OK).body(planos);
    }

    @PostMapping("/planos") //Cadastrar Novo Plano
    public ResponseEntity cadastrarPlano(@RequestBody Planos plano) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarPlano(plano));

    }

     @PutMapping("/planos/{idPlano}") // Alterar Plano
        public ResponseEntity alterarPlano(@RequestBody Planos plano, @PathVariable("idPlano") BigInteger idPlano){
            String retorno = service.alterarPlano(plano, idPlano);
            return ResponseEntity.ok().body(retorno);
        }


    @DeleteMapping("/planos/{idPlano}") //Excluir Plano
    public ResponseEntity excluirPlano(@PathVariable("idPlano") BigInteger idPlano) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.excluirPlano(idPlano));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir plano");
        }
    }
}