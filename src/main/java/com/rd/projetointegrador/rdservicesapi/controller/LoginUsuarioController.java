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

    @GetMapping("/login/{idUsuario}") // BUSCA POR ID
    public ResponseEntity getAcesso(@PathVariable("idUsuario") BigInteger idUsuario) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAcesso(idUsuario));

    }

    @GetMapping("/login") //Busca de todos os logins
    public ResponseEntity getAcessos() {
        List<LoginUsuarioEntity> acessos = service.getAcessos();
        return ResponseEntity.status(HttpStatus.OK).body(acessos);
    }

    @PostMapping("/login") //Cadastrar Novo Login
    public ResponseEntity cadastrarAcesso(@RequestBody LoginUsuario login) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarAcesso(login));

    }

    @PutMapping("/login/{idUsuario}") // Alterar Login
    public ResponseEntity alterarAcesso(@RequestBody LoginUsuario login, @PathVariable("idUsuario") BigInteger idUsuario) {
        String retorno = service.alterarAcesso(login, idUsuario);
        return ResponseEntity.ok().body(retorno);

    }

    //Confirmar se haverá ou não exclusão de acesso
    @DeleteMapping("/login/{idUsuario}") //Excluir Login
    public ResponseEntity excluirAcesso(@PathVariable("idUsuario") BigInteger idUsuario) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.excluirAcesso(idUsuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir login");
        }
    }

}

