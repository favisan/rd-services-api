package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.entity.AgServicoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.StatusEntity;
import com.rd.projetointegrador.rdservicesapi.repository.AgServicoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.StatusRepository;
import com.rd.projetointegrador.rdservicesapi.service.AgServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;

@RestController
public class AgServicoController {

    @Autowired
    private AgServicoService service;


    @GetMapping("/agservico/horarios/{id}")
    public ResponseEntity getAgendamentosPorLoja(@PathVariable("id") BigInteger id) throws ParseException {

        List<AgServicoEntity> agservicos = service.getAgendamentosPorLoja(id);
        return ResponseEntity.ok(agservicos);
    }/*Rretorna todos os agendamentos de determinada loja durante um período especificado.*/


    @GetMapping("/agservico/pedidos/{id}")
    public ResponseEntity getPedidos(@PathVariable("id") BigInteger id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getPedidos(id));
    }/*Retorna todos os pedidos do usuário com id "id" e seus respectivos agendamentos*/


    @GetMapping("/agservico/{id}")
    public ResponseEntity getAgendamentos(@PathVariable("id") BigInteger id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAgendamentos(id));
    }//Retorna os agendamentos do paciente com id "id" por status (Agendado, Cancelado ou Relizado)


    @PutMapping("/agservico/cancelar/{id}")
    public ResponseEntity cancelar(@PathVariable("id") BigInteger id){
        return ResponseEntity.ok().body(service.cancelarAgendamento(id));
    }//Cancelar um agendamento */

}
