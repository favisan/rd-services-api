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
public class InputUsuario {

    //login
    private LoginUsuario loginUsuario;

    //usuario
    private Usuario usuario;

    //contato
    private String ddd;
    private String celular;

    //plano
    private Contrato contrato;

    //cartao
    private Cartao cartao;

}
