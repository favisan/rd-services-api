package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@NoArgsConstructor
@Table(name="TB_TIPO_RECEITA")
public class TipoReceitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_TIPO_RECEITA")
    private BigInteger idTipoReceita;

    @Column(name="DS_TIPO_RECEITA")
    private String dsTipoReceita;

}
