package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@Data
@NoArgsConstructor
public class TipoContato implements Serializable {

    private BigInteger idTipoContato;
    private String dsTipoContato;
}
