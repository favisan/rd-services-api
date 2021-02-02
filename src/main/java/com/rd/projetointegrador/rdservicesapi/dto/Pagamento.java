package com.rd.projetointegrador.rdservicesapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {

    private BigInteger  idPagamento;
    private BigInteger idCartao ;
    private BigInteger idContrato;
    private BigInteger idFormaPgt;
    private BigInteger idNF;
    private BigInteger idAgPaciente;
    private BigInteger idPedido;
    private Double vlPagamento;
    private Date dtPagamento;
    private Integer nrParcela;
}
