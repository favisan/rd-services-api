package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.Agenda;
import com.rd.projetointegrador.rdservicesapi.entity.AgendaEntity;
import com.rd.projetointegrador.rdservicesapi.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @GetMapping("/agenda")
    public ResponseEntity getAgendas() {
        List<AgendaEntity> agendas = agendaService.getAgendas();

        return ResponseEntity.status(HttpStatus.OK).body(agendas);
    }

    @PostMapping("/agenda")
    public ResponseEntity cadastrarAgenda(@RequestBody Agenda agenda) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaService.cadastrarAgenda(agenda));
    }
}
