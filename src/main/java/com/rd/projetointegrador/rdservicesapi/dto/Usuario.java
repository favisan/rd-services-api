package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    BigInteger idUsuario;
    BigInteger idGenero;
    BigInteger idEspMedica;
    BigInteger idUfCrm;
    BigInteger idTipoUsuario;
    String nmUsuario;
    Date dtNascimento;
    String nrCpf;
    String nrCrm;
    String dsEndImg;
}
