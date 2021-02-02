package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.entity.EspMedEntity;
import com.rd.projetointegrador.rdservicesapi.service.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EspecialidadeController {

    @Autowired
    EspecialidadeService service;

    @GetMapping("/especialidades")
    public ResponseEntity getEspecialidades() {
        List<EspMedEntity> especialidades = service.getEspecialidades();
        return ResponseEntity.status(HttpStatus.OK).body(especialidades);
    }
}