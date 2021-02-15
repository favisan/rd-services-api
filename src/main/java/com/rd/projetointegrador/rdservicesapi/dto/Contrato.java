package com.rd.projetointegrador.rdservicesapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contrato {
    //GRUPO1

    private BigInteger idContrato;
    private String dsContrato;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dtVigencia;
    private Planos plano;
    private BigInteger idUsuario;

}
