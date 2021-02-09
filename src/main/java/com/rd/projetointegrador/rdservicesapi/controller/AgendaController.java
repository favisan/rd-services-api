package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class AgendaController {
    @Autowired
    private AgendaService agendaService;

    @GetMapping("/agenda/especialidade")
    public ResponseEntity getEspByAgenda (){
        return ResponseEntity.ok().body(agendaService.getEspByAgenda());
    }

    @GetMapping("agenda/{idTipoConsulta}/{idEspecialidade}")
    public ResponseEntity getAgendaByEsp (@PathVariable("idTipoConsulta") BigInteger idC, @PathVariable("idEspecialidade") BigInteger idE){
        return ResponseEntity.ok().body(agendaService.getAgendaByEspecialidade(idE, idC));
    }

}
