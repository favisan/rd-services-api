package com.rd.projetointegrador.rdservicesapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.annotation.Resource;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "TB_TIPO_REFEICAO")
@Data
public class TipoRefeEntity implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_REFEICAO")
    private BigInteger idTipoRefeicao;

    @Column(name = "DS_TIPO_REFEICAO")
    private String dsTipoRefeicao;

    @JsonIgnore
    @OneToMany(mappedBy = "idTipoRefeicao")
    private List<CardapioEntity> cardapios;
}
