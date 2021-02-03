package com.rd.projetospring.primeiroprojeto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
public class AgServico {

    private BigInteger idAgendamento;
    private BigInteger idServico;
    private BigInteger idLoja;
    private BigInteger idStatus;
    private Date dtDataHora;
    private BigInteger idPedido;
}
