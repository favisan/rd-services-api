package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.entity.TipoRefeEntity;
import com.rd.projetointegrador.rdservicesapi.service.TipoRefeicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class TipoRefeicaoController {
    @Autowired
    private TipoRefeicaoService tipoRefeicaoService;

    //listar todas refeicoes cadastradas para um idPaciente
  // @GetMapping("/listar-refeicoes/{idPaciente}")
  //public ResponseEntity listarRefeicoes(@PathVariable ("idPaciente") BigInteger idPaciente){
  //     return ResponseEntity.status(HttpStatus.OK).body(tipoRefeicaoService.listarRefeicoes(idPaciente));
  //}



    @GetMapping("/listar-refeicoes/{idPaciente}")
    public ResponseEntity getCardapioUsuario (@PathVariable("idPaciente") BigInteger idPaciente) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tipoRefeicaoService.getCardapioUsuario(idPaciente));
    }

    // BUSCA POR ID
    @GetMapping("/tipo-refeicoes/{idTipoRefeicao}")
    public ResponseEntity getTipoRefeicao(@PathVariable ("idTipoRefeicao") BigInteger idTipoRefeicoes) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tipoRefeicaoService.getTipoRefeicao(idTipoRefeicoes));

    }

    //Busca todos os tipos de refeicoes
    @GetMapping("/tipo-refeicoes")
    public ResponseEntity getTipoRefeicoesDTO() {
        List<TipoRefeEntity> tiporefeicoes = tipoRefeicaoService.getTiposRefeicoes();
        return ResponseEntity.status(HttpStatus.OK).body(tiporefeicoes);
    }

}
