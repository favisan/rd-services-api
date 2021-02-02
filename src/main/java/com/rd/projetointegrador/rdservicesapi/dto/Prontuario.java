package com.rd.projetointegrador.rdservicesapi.dto;

import com.rd.projetointegrador.rdservicesapi.entity.AtendimentoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prontuario {

    private BigInteger idProntuario;

    private String dsSubjetivo;

    private String dsObjetivo;

    private String dsAvaliacao;

    private String dsPlano;

    private String dsObservacoes;

    private Atendimento atendimento;

}
