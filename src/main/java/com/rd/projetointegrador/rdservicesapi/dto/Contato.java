package com.rd.projetointegrador.rdservicesapi.dto;

<<<<<<< HEAD
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;

=======

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
>>>>>>> grupo_3

@Data
@NoArgsConstructor
public class Contato {

    private BigInteger idContato;
<<<<<<< HEAD
    private BigInteger idUsuario;
    private BigInteger idLoja;
    private TipoContato tipoContato;
    private String nrDdi;
    private String nrDdd;
    private String nrCep;
    private String dsContato;
=======
    private BigInteger idLoja;
    private String nrDDD;
    private String nrRamal;
    private String dsContato;

>>>>>>> grupo_3
}
