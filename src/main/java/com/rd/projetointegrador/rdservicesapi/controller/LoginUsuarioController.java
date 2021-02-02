package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.LoginUsuario;
import com.rd.projetointegrador.rdservicesapi.entity.LoginUsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.service.LoginUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.math.BigInteger;
import java.util.List;

@RestController
public class LoginUsuarioController {

    @Autowired
    LoginUsuarioService service;

    @GetMapping("/login/{idAcesso}") // BUSCA POR ID
    public ResponseEntity getAcesso(@PathVariable("idAcesso") BigInteger idAcesso) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAcesso(idAcesso));

    }

    @GetMapping("/login") //Busca de todos os logins
    public ResponseEntity getAcessos(@PathParam("idAcesso") BigInteger idAcesso) {
        List<LoginUsuarioEntity> acessos = service.getAcessos(idAcesso);
        return ResponseEntity.status(HttpStatus.OK).body(acessos);
    }

    @PostMapping("/login") //Cadastrar Novo Login
    public ResponseEntity cadastrarAcesso(@RequestBody LoginUsuario login) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarAcesso(login));

    }

    @PutMapping("/login/{idAcesso}") // Alterar Login
    public ResponseEntity alterarAcesso(@RequestBody LoginUsuario login, @PathVariable("idAcesso") BigInteger idAcesso) {
        String retorno = service.alterarAcesso(login, idAcesso);
        return ResponseEntity.ok().body(retorno);

    }

    //Confirmar se haverá ou não exclusão de acesso
    @DeleteMapping("/login/{idAcesso}") //Excluir Login
    public ResponseEntity excluirAcesso(@PathVariable("idAcesso") BigInteger idAcesso) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.excluirAcesso(idAcesso));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir login");
        }
    }

}

