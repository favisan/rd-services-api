package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.service.EspMedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EspMedController {
    @Autowired private EspMedService espMedService;

    //Grupo2 - Consulta às especialidades médicas das agendas disponíveis
    @GetMapping("/especialidade/agenda")
    public ResponseEntity getEspByAgenda (){
        return ResponseEntity.ok().body(espMedService.getEspByAgenda());
    }
}
