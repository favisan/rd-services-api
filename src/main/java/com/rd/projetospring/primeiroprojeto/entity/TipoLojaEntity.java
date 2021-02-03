package com.rd.projetospring.primeiroprojeto.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "TB_TIPO_LOJA")
@Data
public class TipoLojaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_LOJA")
    private BigInteger id;

    @Column(name = "DS_TIPO_LOJA")
    private String tipo;
}
