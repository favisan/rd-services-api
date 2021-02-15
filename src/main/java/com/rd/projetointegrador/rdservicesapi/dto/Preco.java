package com.rd.projetointegrador.rdservicesapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Preco {

    private BigInteger idPreco;
    private Double vlConsulta;

}