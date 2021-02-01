package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "TB_TIPO_CONSULTA")
@Data
@NoArgsConstructor

public class TipoConsultaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_TIPO_CONSULTA")
    private BigInteger idTipoConsulta;

    @Column(name="DS_TIPO_CONSULTA")
    private String dsTipoConsulta;
}
