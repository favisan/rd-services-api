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

    //GRUPO1

    @Autowired LoginUsuarioService service;

    @GetMapping("/login/{idUsuario}") // BUSCA POR ID
    public ResponseEntity getAcessoById(@PathVariable("idUsuario") BigInteger idUsuario) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAcesso(idUsuario));
    }

    @GetMapping("/login/email/{email}") // BUSCA POR E-MAIL
    public ResponseEntity getAcessoByEmail(@PathVariable("email") String email) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAcessoByEmail(email));
    }

    @GetMapping("/login") //Busca de todos os logins
    public ResponseEntity getAcessos() {
        List<LoginUsuarioEntity> acessos = service.getAcessos();
        return ResponseEntity.status(HttpStatus.OK).body(acessos);
    }

    //PÁGINA LOGIN CLIENTE -------------------------------------------------------------------------------
    @PostMapping("/login/cliente")
    public ResponseEntity getAcessoCliente(@RequestBody LoginUsuario login) throws NoSuchAlgorithmException {
        return service.validarAcessoCliente(login);
    }

    @PostMapping("/login/esqueceu-senha") // BUSCA POR E-MAIL
    public ResponseEntity esqueceuASenha(@RequestBody String email) {
        return service.esqueceuASenha(email);
    }

    //-----------------------------------------------------------------------------------------------------


    @PostMapping("/login") //Cadastrar Novo Login
    public ResponseEntity cadastrarAcesso(@RequestBody LoginUsuario login) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarAcesso(login));

    }

    @PutMapping("/login/cliente/{idUsuario}") // Alterar Login
    public ResponseEntity alterarAcessoCliente(@RequestBody LoginUsuario login, @PathVariable("idUsuario") BigInteger idUsuario) {
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



    //GRUPO4
    //VALIDAR ACESSO
    @GetMapping("/login/medico")
    public ResponseEntity getAcesso(@RequestBody LoginUsuario login) throws NoSuchAlgorithmException {
        try{
         String retorno = service.validarAcesso(login);
        return ResponseEntity.status(HttpStatus.OK).body(retorno);
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
    public ResponseEntity acessoSemSenha(@RequestBody OutputMedico medico) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.acessoSemSenha(medico));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Dados invalidos");
        }
    }
}
