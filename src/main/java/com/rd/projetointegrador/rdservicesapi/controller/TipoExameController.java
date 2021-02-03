package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.entity.TipoExameEntity;
import com.rd.projetointegrador.rdservicesapi.service.TipoExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TipoExameController {

    @Autowired
    TipoExameService service;

    @GetMapping("/tiposexames")
    public ResponseEntity listarTiposDeExames() {
        List<TipoExameEntity> tiposExames = service.listarTiposDeExames();
        return ResponseEntity.status(HttpStatus.OK).body(tiposExames);
    }
}
