package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "TB_STATUS_CONSULTA")
@Data
public class StatusConsultaEntity implements Serializable {

    @Id
    @Column(name = "ID_STATUS_CONSULTA")
    private BigInteger idStatusConsulta;

    @Column(name = "DS_STATUS_CONSULTA")
    private String dsStatusConsulta;
}
