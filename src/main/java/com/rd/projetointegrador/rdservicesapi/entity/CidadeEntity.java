package com.rd.projetointegrador.rdservicesapi.entity;


import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigInteger;


@Entity
@Table(name = "TB_CIDADE")
@Data
public class CidadeEntity implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)  // anotalação usada para resolver o erro Hikari.. que estava dando em outros projetos
    @Column(name="ID_CIDADE")
    private BigInteger idCidade;

    @Column(name="ID_UF")
    private BigInteger idUf;

    @Column(name="CD_CIDADE_IBGE")
    private BigInteger cdCidadeIbge;

    @Column(name="DS_CIDADE")
    private String dsCidade;

}
