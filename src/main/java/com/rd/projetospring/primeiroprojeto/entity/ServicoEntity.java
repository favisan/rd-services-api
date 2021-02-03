package com.rd.projetospring.primeiroprojeto.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "TB_SERVICO")
@Data
public class ServicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SERVICO")
    private BigInteger id;

    @Column(name = "DS_SERVICO")
    private String nome;

    @Column(name = "VL_PRECO")
    private float preco;

}
