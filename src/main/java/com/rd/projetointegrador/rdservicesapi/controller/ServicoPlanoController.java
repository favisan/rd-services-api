package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.ServicoPlano;
import com.rd.projetointegrador.rdservicesapi.entity.ServicoPlanoEntity;

import com.rd.projetointegrador.rdservicesapi.service.ServicoPlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.math.BigInteger;
import java.util.List;

@RestController
public class ServicoPlanoController {

    @Autowired
    ServicoPlanoService service;

    @GetMapping("/servicoPlano/{idServicoPlano}") // BUSCA POR ID
    public ResponseEntity getServicoPlano(@PathVariable("idServicoPlano") BigInteger idServicoPlano) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getServicoPlano(idServicoPlano));

    }

    @GetMapping("/servicoPlano") //Busca de todos os Servicos
    public ResponseEntity getServicosPlano() {
        List<ServicoPlanoEntity> servicos = service.getServicosPlano();
        return ResponseEntity.status(HttpStatus.OK).body(servicos);
    }

    @PostMapping("/servicoPlano") //Cadastrar Novo Servico
    public ResponseEntity cadastrarServicoPlano(@RequestBody ServicoPlano servico) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarServicoPlano(servico));

    }

    @PutMapping("/servicoPlano/{idServicoPlano}") // Alterar Servico
    public ResponseEntity alterarServicoPlano(@RequestBody ServicoPlano servico, @PathVariable("idServicoPlano") BigInteger idServicoPlano){
        String retorno = service.alterarServicoPlano(servico, idServicoPlano);
        return ResponseEntity.ok().body(retorno);
    }
    @DeleteMapping("/servicoPlano/{idServicoPlano}") //Excluir Servico
    public ResponseEntity excluirServicoPlano(@PathVariable("idServicoPlano") BigInteger idServicoPlano) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.excluirServicoPlano(idServicoPlano));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir serviço");
        }
    }
}



