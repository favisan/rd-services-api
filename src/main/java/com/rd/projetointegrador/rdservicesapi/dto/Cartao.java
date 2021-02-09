package com.rd.projetointegrador.rdservicesapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cartao {
    //GRUPO1

    private BigInteger idCartao;
    private BigInteger idUsuario;
    private String nrCartao;
    private String codSeguranca;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dtValidade;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dtEmissao;
    //private BigInteger idPaciente;
}
