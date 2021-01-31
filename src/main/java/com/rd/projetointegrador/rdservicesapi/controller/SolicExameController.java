package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.SolicExame;
import com.rd.projetointegrador.rdservicesapi.service.SolicExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class SolicExameController {
    @Autowired
    SolicExameService service;


    @PostMapping("/solic_exame")
    public ResponseEntity gravarSolicExame(@RequestBody SolicExame solicExame) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarSolicExame(solicExame));
    }
}
