package com.rd.projetointegrador.rdservicesapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    //GRUPO1

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
    private BigInteger idPreco;

    private List<Endereco> enderecos;
}
