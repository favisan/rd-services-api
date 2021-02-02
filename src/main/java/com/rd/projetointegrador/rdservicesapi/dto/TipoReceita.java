package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoReceita {

    private BigInteger idTipoReceita;

    private String dsTipoReceita;

}
