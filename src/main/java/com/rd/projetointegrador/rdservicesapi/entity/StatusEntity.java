package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "TB_STATUS_AG_SERVICO")
@Data
public class StatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_STATUS")
    private BigInteger id;

    @Column(name = "DS_STATUS")
    private String nome;

}
