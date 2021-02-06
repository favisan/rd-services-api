package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.InputMedico;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService service;

    //BUSCAR MEDICO POR ID
    @GetMapping("/medico/{id}")
    public ResponseEntity getMedico(@PathVariable("id") BigInteger id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getMedico(id));
    }

    //BUSCAR CPF PARA IMPEDIR CADATRO COM MESMO CPF
    @GetMapping("/usuarioCpf/{nrCpf}")
    public ResponseEntity consultarPorCpf(@PathVariable("nrCpf") String cpf){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.consultarPorCpf(cpf));
    }

    //LISTAR TODOS OS MEDICOS
    @GetMapping("/medicos")
    public ResponseEntity getmedicos(){
        List<UsuarioEntity> usuarios = service.getMedicos();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    //EXIBIR TELA DE PERFIL DO MEDICO
    @GetMapping("/perfilMedico/{idMedico}/{idUf}")
    public ResponseEntity mostrarTelaPerfil(@PathVariable("idMedico") BigInteger idMedico,@PathVariable("idUf") BigInteger idUf){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.mostrarTelaPerfil(idMedico,idUf));
    }

    //ALTERAR CADASTRO DE PERFIL DO MEDICO
    @PutMapping("/medico/{idUsuario}")
    public ResponseEntity alterarMedico(@RequestBody InputMedico inputMedico, @PathVariable("idUsuario") BigInteger id){
        String retorno = service.alterarMedico(inputMedico, id);
        return ResponseEntity.ok().body(retorno);
    }

    //EXIBIR LISTAS DA TELA DE CADASTRO DO MEDICO
    @GetMapping("/cadastroMedico/{idUf}")
    public ResponseEntity mostrarTelaCadastro(@PathVariable("idUf") BigInteger idUf){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.mostrarTelaCadastro(idUf));
    }

    //CADASTRAR MEDICO
    @PostMapping("/medico")
    public ResponseEntity cadastrarMedico(@RequestBody InputMedico inputMedico) {
        if (service.consultarPorCpf(inputMedico.getNrCpf()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cpf já cadastrado");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarMedico(inputMedico));
        }
    }
}
