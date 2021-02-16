package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.service.TipoConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class TipoConsultaController {
    @Autowired private TipoConsultaService service;

    //Grupo2 - Consultar tipo de consulta pela id
    @GetMapping("/tipoConsulta/{idAgConsulta}")
    public ResponseEntity listarTipoConsultaPorId(@PathVariable("idTipoConsulta") BigInteger idTipoConsulta){
        return ResponseEntity.status(HttpStatus.OK).body(service.getTipoConsultabyId(idTipoConsulta));
    }
}
