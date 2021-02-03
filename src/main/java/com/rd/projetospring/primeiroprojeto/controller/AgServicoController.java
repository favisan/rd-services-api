package com.rd.projetospring.primeiroprojeto.controller;

import com.rd.projetospring.primeiroprojeto.dto.Loja;
import com.rd.projetospring.primeiroprojeto.entity.AgServicoEntity;
import com.rd.projetospring.primeiroprojeto.entity.LojaEntity;
import com.rd.projetospring.primeiroprojeto.repository.AgServicoRespository;
import com.rd.projetospring.primeiroprojeto.repository.LojaRepository;
import com.rd.projetospring.primeiroprojeto.service.AgServicoService;
import com.rd.projetospring.primeiroprojeto.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class AgServicoController {

    @Autowired
    private AgServicoRespository respository;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private AgServicoService service;

    @GetMapping("/agservico")
    public ResponseEntity getAgendamentos() throws ParseException {

        String from = "2021-02-02 00:00:01";
        String to = "2021-02-02 23:59:59";

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date data1 = formato.parse(from);
        Date data2 = formato.parse(to);

        LojaEntity loja = lojaRepository.findById(BigInteger.valueOf(9l)).get();

        List<AgServicoEntity> agservicos = respository.findByIdLojaAndDtDataHoraBetween(loja,data1, data2);
        return ResponseEntity.ok(agservicos);
    }

//    @GetMapping("/agservico/{id}")
//    public ResponseEntity getPedidos(@PathVariable("id") BigInteger id){
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(service.getPedidos(id));
//    }

    @GetMapping("/agservico/{id}")
    public ResponseEntity getAgendamentos(@PathVariable("id") BigInteger id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAgendamentos(id));
    }


}
