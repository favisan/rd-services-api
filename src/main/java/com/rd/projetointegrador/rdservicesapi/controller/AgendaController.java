package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.Agenda;
import com.rd.projetointegrador.rdservicesapi.entity.AgendaEntity;
import com.rd.projetointegrador.rdservicesapi.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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

    @GetMapping("/agendas")
    public ResponseEntity getAgendasPorData(@RequestBody Date diaDisponivel) throws ParseException {

        try{
            List<Agenda> agendas = agendaService.getAgendasPorData(diaDisponivel);
            return ResponseEntity.status(HttpStatus.OK).body(agendas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar lista de agendas");
        }
    }

    @PostMapping("/agenda")
    public ResponseEntity cadastrarAgenda(@RequestBody Agenda agenda) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaService.cadastrarAgenda(agenda));
    }

    @PostMapping("/agendas")
    public ResponseEntity cadastrarAgendaPorDia(@RequestBody List<Agenda> agendas) throws ParseException {
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaService.cadastrarAgendaPorDia(agendas));
    }

    @DeleteMapping("/agenda")
    public ResponseEntity excluirAgendas(@RequestBody List<Agenda> agendas) {
        return ResponseEntity.status(HttpStatus.OK).body(agendaService.excluirAgendas(agendas));
    }
}
