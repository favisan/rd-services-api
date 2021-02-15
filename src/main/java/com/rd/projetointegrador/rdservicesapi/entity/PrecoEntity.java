package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "TB_PRECO")
@Data
public class PrecoEntity implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRECO")
    private BigInteger idPreco;

    @Column(name = "VL_CONSULTA")
    private Double vlConsulta;
}
