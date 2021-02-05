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
public class UsuarioSaida {

    private BigInteger idUsuario;
    private BigInteger genero;
    private BigInteger espMedica;
    private BigInteger ufCrm;
    private BigInteger tipoUsuario;
    private String nmNome;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dtNascimento;
    private String nrCpf;
    private String nrCrm;
    private String dsEndImg;
    private BigInteger preco;

}
