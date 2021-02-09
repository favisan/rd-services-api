package br.com.rd.projetointegrador.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receita {
    private BigInteger idReceita;
    private String nomeReceita;
    private String dsReceita;
    private Float qtCalorias;
    private Float qtRendimento;
    private String endImagem;


}
