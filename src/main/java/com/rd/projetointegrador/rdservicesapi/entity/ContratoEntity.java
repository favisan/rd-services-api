package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Data
@Table (name="TB_CONTRATO")
public class ContratoEntity  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_CONTRATO")
    private BigInteger idContrato;

    @Column(name="DS_CONTRATO")
    private String dsContrato;

    @Column(name="DT_VIGENCIA")
    private Date dtVigencia;

    @Column(name="ID_PLANO") //FK TB_PLANO
    private BigInteger idPlano;

    @Column(name="ID_USUARIO") //FK TB_USUARIO
    private BigInteger idUsuario;
}
