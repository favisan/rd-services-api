package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
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
