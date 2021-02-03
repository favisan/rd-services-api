package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;


@Data
@NoArgsConstructor
public class Contato {

    private BigInteger idContato;
    private BigInteger idUsuario;
    private BigInteger idLoja;
    private TipoContato tipoContato;
    private String nrDdi;
    private String nrDdd;
    private String nrCep;
    private String dsContato;
}
