package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "TB_PRECO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrecoEntity implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRECO")
    private BigInteger idPreco;

    @Column(name = "VL_CONSULTA")
    private Double vlConsulta;
}
