package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "TB_CONTATO")
@Data
public class ContatoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTATO")
    private BigInteger idContato;

    @Column(name = "ID_USUARIO")
    private BigInteger idUsuario;

    @ManyToOne
    @JoinColumn(name = "ID_LOJA")
    private LojaEntity loja;

    @Column(name = "ID_TIPO_CONTATO")
    private BigInteger idTipoContato;

    @Column(name = "NR_DDI")
    private String nrDdi;

    @Column(name = "NR_DDD")
    private String nrDdd;

    @Column(name = "NR_RAMAL")
    private String nrRamal;

    @Column(name = "DS_CONTATO")
    private String dsContato;
}
