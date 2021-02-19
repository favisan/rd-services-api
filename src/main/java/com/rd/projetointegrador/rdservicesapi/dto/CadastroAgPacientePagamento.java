package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.Data;
import java.math.BigInteger;

//Grupo2 - DTO criada para o serviço setAgPaciente e setPagamento
@Data
public class CadastroAgPacientePagamento {
    private BigInteger idAgenda;
    private BigInteger idUsuario;
    private Integer nrParcelas;
    private TipoPagamento tipoPgto;
    private Cartao cartao;
}
