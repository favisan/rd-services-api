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
    private String dsEmail;
    private String dsSenha;

    //usuario
    private BigInteger idTipoUsuario;
    private String nmNome;
    private BigInteger idGenero;
    private String nrCpf;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private Date dtNascimento;
    //private Endereco endereco;
    private String dsEndImg;

    //contato
    private String ddd;
    private String celular;

    //cartao
    private String nrCartao;
    private String nrCodSeguranca;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private Date dtValidade;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private Date dtEmissao;

}
