package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.dto.Cidade;
import com.rd.projetointegrador.rdservicesapi.entity.CidadeEntity;
import com.rd.projetointegrador.rdservicesapi.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class CidadeController {

    @Autowired
    CidadeService service;

    @GetMapping("/cidade/{id}")
    public ResponseEntity buscarCidadeId(@PathVariable("id") BigInteger id) {

        Cidade c = service.buscarCidadeId(id);
        return ResponseEntity.status(HttpStatus.OK).body(c);
    }

    @GetMapping("/cidade")
    public ResponseEntity getCidades() {

        List<Cidade> cidades = service.listarCidade();
        return ResponseEntity.status(HttpStatus.OK).body(cidades);

    }

    @PostMapping("/cidade")
    public ResponseEntity cadastrarCidade(@RequestBody Cidade cidade) {

//        if(cidade.getNomeCidade() == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Atributo nome é obrogatório");
//        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarCidade(cidade));
    }

    @PutMapping("/cidade/{id}")
    public ResponseEntity alterarCidade(@RequestBody Cidade cidade, @PathVariable("id") BigInteger id) {
        System.out.println("ID: " + id);

        return ResponseEntity.status(HttpStatus.OK).body(service.alterarCidade(cidade, id));
    }


    @DeleteMapping("/cidade/{id}")
    public ResponseEntity excluirCidade(@PathVariable("id") BigInteger id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.excluirCidade(id));
    }

    @GetMapping("/cidadeBuscar/{dsCidade}")
    public ResponseEntity buscarCidadeId(@PathVariable("dsCidade") String dsCidade) {

        List<CidadeEntity> c = service.consultarPorNome(dsCidade);
        return ResponseEntity.status(HttpStatus.OK).body(c);
    }
}
