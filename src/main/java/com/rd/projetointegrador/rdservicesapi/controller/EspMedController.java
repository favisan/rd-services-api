package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.service.EspMedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class EspMedController {

    @Autowired
    private EspMedService service;

    @GetMapping("/especialidade")
    public ResponseEntity getEspecialidades(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getEspecialidades());
    }
}
