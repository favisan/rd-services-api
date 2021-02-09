package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.Contrato;
import com.rd.projetointegrador.rdservicesapi.dto.TipoUsuario;
import com.rd.projetointegrador.rdservicesapi.entity.ContratoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.TipoUsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.service.ContratoService;
import com.rd.projetointegrador.rdservicesapi.service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.math.BigInteger;
import java.util.List;

@RestController
public class TipoUsuarioController {
    //GRUPO1

    @Autowired
    TipoUsuarioService service;

    @GetMapping("/tipoUsuario/{idTipoUsuario}") // BUSCA POR ID
    public ResponseEntity getTipoUsuario(@PathVariable("idTipoUsuario") BigInteger idTipoUsuario){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getTipoUsuario(idTipoUsuario));

    }
    @GetMapping("/tipoUsuario") //Busca de todos os Tipos de Usuario
    public ResponseEntity getTiposUsuario(@PathParam("idTipoUsuario") BigInteger idTipoUsuario) {
        List<TipoUsuarioEntity> tiposUsuario = service.getTiposUsuario(idTipoUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(tiposUsuario);
    }
    @PostMapping("/tipoUsuario") //Cadastrar Novo Tipo de Usuario
    public ResponseEntity cadastrarTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarTipoUsuario(tipoUsuario));
    }

    @PutMapping("/tipoUsuario/{idTipoUsuario}") // Alterar Tipo Usuario
    public ResponseEntity alterarTipoUsuario(@RequestBody TipoUsuario tipoUsuario, @PathVariable("idTipoUsuario") BigInteger idTipoUsuario){
        String retorno = service.alterarTipoUsuario(tipoUsuario, idTipoUsuario);
        return ResponseEntity.ok().body(retorno);

    }

    @DeleteMapping("/tipoUsuario/{idTipoUsuario}") //Excluir TipoUsuario
    public ResponseEntity excluirTipoUsuario(@PathVariable("idTipoUsuario") BigInteger idTipoUsuario) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.excluirTipoUsuario(idTipoUsuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir.");
        }
    }
}
