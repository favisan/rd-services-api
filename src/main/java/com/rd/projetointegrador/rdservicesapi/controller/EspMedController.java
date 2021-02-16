package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.service.EspMedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class EspMedController {
    @Autowired private EspMedService espMedService;

    //Grupo2 - Consulta às especialidades médicas das agendas disponíveis
    @GetMapping("/especialidade/agenda/{idTipoConsulta}")
    public ResponseEntity getEspByAgenda (@PathVariable("idTipoConsulta") BigInteger id){
        return ResponseEntity.ok().body(espMedService.getEspByAgenda(id));
    }

    //Grupo2 - Consultar espMed pela id
    @GetMapping("/especialidade/{idEspMed}")
    public ResponseEntity listarTipoConsultaPorId(@PathVariable("idEspMed") BigInteger idEspMed){
        return ResponseEntity.status(HttpStatus.OK).body(espMedService.getEspMedbyId(idEspMed));
    }
}
