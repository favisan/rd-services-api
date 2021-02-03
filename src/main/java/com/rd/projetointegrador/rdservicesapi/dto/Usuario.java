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
public class Usuario {

    private BigInteger idUsuario;
    private BigInteger idGenero;
    private BigInteger idEspMedica;
    private BigInteger idUfCrm;
    private BigInteger idTipoUsuario;
    private String nmNome;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dtNascimento;
    private String nrCpf;
    private String nrCrm;
    private String dsEndImg;

    //private List<Endereco> enderecos;
}
