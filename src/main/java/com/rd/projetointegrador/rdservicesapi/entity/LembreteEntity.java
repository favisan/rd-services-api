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
@Table(name="TB_LEMBRETE")
@Data

public class LembreteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_LEMBRETE")
    private BigInteger idLembrete;

//    @ManyToOne
//    @JoinColumn (name="ID_PACIENTE")
//    private UsuarioEntity paciente;

    @Column(name="ID_PACIENTE")
    private BigInteger idPaciente;

    @ManyToOne
    @JoinColumn (name="ID_LEMBRETE_INTERVALO")
    private LembreteIntervaloEntity lembreteIntervalo;

    @Column(name="NM_TITULO")
    private String nmTitulo;

    @Column(name="DS_LEMBRETE")
    private String dsLembrete;

    @Column(name="DT_LEMBRETE")
    private Date dtLembrete;

    @Column(name="DT_CRIACAO")
    private Date dtCriacao;

    @Column(name="HR_HORA")
    private Date hrHora;

    @Column(name="NR_REPETICAO")
    private Integer nrRepeticao;
}
