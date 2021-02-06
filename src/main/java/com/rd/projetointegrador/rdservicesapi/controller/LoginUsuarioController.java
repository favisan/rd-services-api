package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.LoginUsuario;
import com.rd.projetointegrador.rdservicesapi.dto.OutputMedico;
import com.rd.projetointegrador.rdservicesapi.entity.LoginUsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.service.LoginUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class LoginUsuarioController {

    @Autowired
    LoginUsuarioService service;

    //VALIDAR ACESSO
    @GetMapping("/login")
    public ResponseEntity getAcesso(@RequestBody LoginUsuario login) throws NoSuchAlgorithmException {
        try{
        return ResponseEntity.status(HttpStatus.OK).body(service.validarAcesso(login));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Dados invalidos");
        }
    }

    //ALTERAR LOGIN E SENHA SE ACESSO TELA PERFIL DO MEDICO
    @PutMapping("/login/{idUsuario}")
    public ResponseEntity alterarAcesso(@RequestBody LoginUsuario login, @PathVariable("idUsuario") BigInteger idUsuario) throws NoSuchAlgorithmException {
        String retorno = service.alterarDadosLogin(login, idUsuario);
        return ResponseEntity.ok().body(retorno);
    }

    //ESQUECI A SENHA
    @GetMapping("/esqueci")
    public ResponseEntity acessoSemSenha(@RequestBody String nome, String cpf, String crm) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.acessoSemSenha(nome, cpf, crm));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Dados invalidos");
        }
    }
}