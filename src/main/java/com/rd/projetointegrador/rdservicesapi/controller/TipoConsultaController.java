package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.entity.TipoConsultaEntity;
import com.rd.projetointegrador.rdservicesapi.service.TipoConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController

public class TipoConsultaController {

    @Autowired
    private TipoConsultaService service;

    @GetMapping("/tipoConsulta")

    public ResponseEntity getTipoConsulta () {

        List<TipoConsultaEntity> tipoConsulta = service.getTipoConsulta();

        return ResponseEntity.status(HttpStatus.OK).body(tipoConsulta);

    }
}
