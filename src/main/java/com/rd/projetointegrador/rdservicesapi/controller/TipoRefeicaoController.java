package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.service.TipoRefeicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TipoRefeicaoController {
    @Autowired
    private TipoRefeicaoService tipoRefeicaoService;

    @GetMapping("/cardapio/listarefeicoes")
    public ResponseEntity listarRefeicoes(){
        return ResponseEntity.status(HttpStatus.OK).body(tipoRefeicaoService.listarRefeicoes());
    }
}
