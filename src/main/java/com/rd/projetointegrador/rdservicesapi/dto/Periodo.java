package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Periodo {
    private BigInteger idPeriodo;
    private String dsPerido;
    private Time horaInicial;
    private Time horaFinal;
}
