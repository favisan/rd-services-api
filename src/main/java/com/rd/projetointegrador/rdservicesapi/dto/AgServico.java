package com.rd.projetointegrador.rdservicesapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dtDataHora;
    private BigInteger idPedido;

    private String endLoja;
    private String dsServico;
}
