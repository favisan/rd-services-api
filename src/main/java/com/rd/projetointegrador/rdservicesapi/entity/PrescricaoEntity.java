package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name="TB_PRESCRICAO")
@Data
public class PrescricaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PRESCRICAO")
    private BigInteger idPrescricao;

    @Column(name="ID_MEDICACAO")
    private BigInteger idMedico;

    @Column(name="ID_FORMA_FARMAC")
    private BigInteger idFormFarmac;

    @Column(name="ID_VIA_ADM")
    private BigInteger idViaAdm;

    @Column(name="VL_QUANTIDADE")
    private Integer vlQuantidade;

    @Column(name="VL_CONCENTRACAO")
    private Float vlConcentracao;

    @Column(name="DS_ORIENTACOES")
    private String dsOrientacoes;

//    @ManyToMany(mappedBy = "prescricoes")
//    private List<ReceituarioEntity> receituarios;

}
