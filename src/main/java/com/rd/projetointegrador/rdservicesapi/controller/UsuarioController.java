package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.Usuario;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.TipoUsuarioRepository;
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
    public ResponseEntity getMedico(@PathVariable("id") BigInteger id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getMed(id));
    }
    @GetMapping("/usuarioCpf/{nrCpf}")
    public ResponseEntity consultarPorCpf(@PathVariable("nrCpf") String cpf){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.consultarPorCpf(cpf));
    }

    @GetMapping("/usuario")
    public ResponseEntity getMedicos(){
        List<UsuarioEntity> medicos = service.getUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(medicos);
    }

    @PostMapping("/usuarioMedico")
    public ResponseEntity cadastrar(@RequestBody Usuario usuario) {
        if (service.consultarPorCpf(usuario.getNrCpf()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cpf já cadastrado");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarMedico(usuario));
        }
    }

    @PutMapping("/usuarioMedico/{idUsuario}")
    public ResponseEntity alterar(@RequestBody Usuario usuario, @PathVariable("idUsuario") BigInteger id){
        String retorno = service.alterar(usuario, id);
        return ResponseEntity.ok().body(retorno);
    }
}
