package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescricao {

    private BigInteger idPrescricao;

    private BigInteger idMedicacao;

    private BigInteger idFormaFarmac;

    private BigInteger idViaAdm;

    private Integer vlQuantidade;

    private Float vlConcentracao;

    private String dsOrientacoes;

}
