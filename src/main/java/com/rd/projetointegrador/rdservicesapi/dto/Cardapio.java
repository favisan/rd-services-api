package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cardapio {
    private BigInteger idCardapio;
    private BigInteger idPaciente;
    private BigInteger idUsuario;
    private BigInteger idMedico;
    private TipoRefeicao idTipoRefeicao;
    private String nomeReceita;
    private Float qtCalorias;
    private Float qtRendimento;
    private String dsDescricao;
}
