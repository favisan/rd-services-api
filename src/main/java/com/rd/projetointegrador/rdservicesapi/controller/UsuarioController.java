package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.Usuario;
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

    @GetMapping("/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable("id") BigInteger id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getUsuario(id));
    }
    @GetMapping("/usuarioCpf/{nrCpf}")
    public ResponseEntity consultarPorCpf(@PathVariable("nrCpf") String cpf){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.consultarPorCpf(cpf));
    }

    @GetMapping("/usuario")
    public ResponseEntity getUsuarios(){
        List<UsuarioEntity> usuarios = service.getUsuario();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @PostMapping("/usuario")
    public ResponseEntity cadastrar(@RequestBody Usuario usuario) {
        if (service.consultarPorCpf(usuario.getNrCpf()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cpf já cadastrado");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(usuario));
        }
    }

    @PutMapping("/usuario/{idUsuario}")
    public ResponseEntity alterar(@RequestBody Usuario usuario, @PathVariable("idUsuario") BigInteger id){
        String retorno = service.alterar(usuario, id);
        return ResponseEntity.ok().body(retorno);
    }
}
