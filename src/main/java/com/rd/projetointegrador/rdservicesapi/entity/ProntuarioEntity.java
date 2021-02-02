package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name="TB_PRONTUARIO")
@Data
@NoArgsConstructor
public class ProntuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PRONTUARIO")
    private BigInteger idProntuario;

    @Column(name="DS_SUBJETIVO")
    private String dsSubjetivo;

    @Column(name="DS_OBJETIVO")
    private String dsObjetivo;

    @Column(name="DS_AVALIACAO")
    private String dsAvaliacao;

    @Column(name="DS_PLANO")
    private String dsPlano;

    @Column(name="DS_OBSERVACOES")
    private String dsObservacoes;

//    @OneToOne(mappedBy = "TB_PRONTUARIO")
//    private AtendimentoEntity atendimento;

}
