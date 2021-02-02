package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name="TB_PRESCRICAO")
@Data
@NoArgsConstructor
public class PrescricaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PRESCRICAO")
    private BigInteger idPrescricao;

    @Column(name="ID_MEDICACAO")
    private BigInteger idMedicacao;

    @Column(name="ID_FORMA_FARMAC")
    private BigInteger idFormaFarmac;

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
