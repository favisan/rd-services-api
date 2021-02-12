package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.entity.AgPacienteEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import com.rd.projetointegrador.rdservicesapi.service.AgPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class AgPacienteController {

    @Autowired private AgPacienteService service;
    @Autowired private UsuarioRepository usuarioRepository;

    @PostMapping ("/agPaciente/cadastrar")
    public ResponseEntity <String> cadastrarAgPaciente(@RequestBody BigInteger idAgenda, BigInteger idUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body(service.setAgPaciente(idAgenda, idUsuario));

    }


    @CrossOrigin

    @GetMapping("/agPaciente/{idUsuario}")
    public ResponseEntity listarAgPaciente(@PathVariable("idUsuario") BigInteger idUsuario){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAgPaciente(usuarioRepository.findById(idUsuario)));
    }

    @PutMapping ("/agPaciente/mudar-status/{idAgPaciente}")
    public ResponseEntity cancelarConsulta(@PathVariable("idAgPaciente") BigInteger idAgPaciente){
        return ResponseEntity.status(HttpStatus.OK).body(service.cancelarAgPaciente(idAgPaciente));
    }

}
