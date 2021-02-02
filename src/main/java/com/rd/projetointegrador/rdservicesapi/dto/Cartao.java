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
public class Cartao {

    private BigInteger idCartao;
    private BigInteger idUsuario;
    private String nrCartao;
    private String codSeguranca;
    private Date dtValidade;
    private Date dtEmissao;
<<<<<<< HEAD
    private BigInteger idPaciente;
=======

>>>>>>> 7f248b8280510993492a1d87160ae621bd6a4887
}
