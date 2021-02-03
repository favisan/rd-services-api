package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.FormularioCadastro;
import com.rd.projetointegrador.rdservicesapi.dto.FormularioMeusDados;
import com.rd.projetointegrador.rdservicesapi.dto.InputUsuario;
import com.rd.projetointegrador.rdservicesapi.dto.Usuario;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.math.BigInteger;
import java.util.List;


@RestController
public class UsuarioController {
    @Autowired
    UsuarioService service;


    @GetMapping("/usuario/{idUsuario}") // BUSCA POR ID
    public ResponseEntity getUsuario(@PathVariable("idUsuario") BigInteger idUsuario) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getUsuarioDTO(idUsuario));

    }

    @GetMapping("/usuario")
    public ResponseEntity getUsuarios(@PathParam("idUsuario") BigInteger idUsuario){
        List<Usuario> usuarios = service.getUsuariosDTO();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }


    //PÁGINA DE CADASTRO
    @GetMapping("/usuario/cadastro")
    public ResponseEntity getFormularioCadastro(){
        FormularioCadastro formularioCadastro = service.getFormularioCadastro();
        return ResponseEntity.status(HttpStatus.OK).body(formularioCadastro);
    }

    //PÁGINA MEUS-DADOS
    @GetMapping("/usuario/meus-dados/{id}")
    public ResponseEntity getFormularioMeusDados(@PathParam("idUsuario") BigInteger idUsuario){
        FormularioMeusDados formularioMeusDados = service.getFormularioMeusDados(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(formularioMeusDados);
    }

    //POST PÁGINA DE CADASTRO (Novo Usuario + Login + Contato + Cartao + Contrato)
    @PostMapping("/usuario/cadastro")
    public ResponseEntity cadastrarUsuario(@RequestBody InputUsuario inputUsuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarUsuario(inputUsuario));
    }



    @PostMapping("/usuario") //Cadastrar Novo Usuario
    public ResponseEntity cadastrarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarUsuario(usuario));

    }

    @PutMapping("/usuario/{idUsuario}") // Alterar Usuario
    public ResponseEntity alterarUsuario(@RequestBody Usuario usuario, @PathVariable("idUsuario") BigInteger idUsuario){
        String retorno = service.alterarUsuario(usuario, idUsuario);
        return ResponseEntity.ok().body(retorno);

    }

    @DeleteMapping("/usuario/{idUsuario}") //Excluir Usuario
    public ResponseEntity excluirUsuario(@PathVariable("idUsuario") BigInteger idUsuario) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.excluirUsuario(idUsuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir usuário");
        }
    }
}
