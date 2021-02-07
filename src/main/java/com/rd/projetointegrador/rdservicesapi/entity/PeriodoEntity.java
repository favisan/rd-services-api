package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Time;

@Entity
@Table(name="TB_PERIODO")
@Data
public class PeriodoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PERIODO")
    private BigInteger idPeriodo;

    @Column(name = "DS_PERIODO")
    private String dsPeriodo;

    @Column(name="TM_HORA_INICIAL")
    private Time horaInicial;
}
