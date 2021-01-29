package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class Contrato {

    private BigInteger idContrato;
    private String dsContrato;
    private Date dtVigencia;
    private BigInteger idPlano;
    private BigInteger idUsuario;

}
