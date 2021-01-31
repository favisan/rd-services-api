package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contrato {

    private BigInteger idContrato;
    private String dsContrato;
    private Date dtVigencia;
    private BigInteger idPlano;
    private BigInteger idUsuario;

}
