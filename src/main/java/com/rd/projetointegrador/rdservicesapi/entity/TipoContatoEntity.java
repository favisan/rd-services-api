package com.rd.projetointegrador.rdservicesapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "TB_TIPO_CONTATO")
public class TipoContatoEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //na tb nao esta como auto incremento
    @Column(name = "ID_TIPO_CONTATO")
    private BigInteger idTipoContato;

    @Column(name = "DS_TIPO_CONTATO")
    private String dsTipoContato;
}
