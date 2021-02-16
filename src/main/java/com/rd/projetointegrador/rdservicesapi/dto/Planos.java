package com.rd.projetointegrador.rdservicesapi.dto;

import com.rd.projetointegrador.rdservicesapi.entity.ServicoPlanoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Planos {
    //GRUPO1

    private BigInteger idPlano;
    private String nmPlano;
    private String dsPlano;
    private Double vlPlano;
    //private BigInteger idServicoPlano;
    private List<ServicoPlano> servicos;

}
