package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "TB_UF")
@Data
public class UfEntity implements Serializable {

    @Id
    @Column(name = "ID_UF")
    private BigInteger idUf;

    @Column(name = "DS_UF")
    private String dsUf;
}