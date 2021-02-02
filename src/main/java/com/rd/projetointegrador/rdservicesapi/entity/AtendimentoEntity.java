package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name="TB_ATENDIMENTO")
@Data
@NoArgsConstructor
public class AtendimentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_ATENDIMENTO")
    private BigInteger idAtendimento;

    @ManyToOne
    @JoinColumn(name="ID_PACIENTE")
    private UsuarioEntity paciente;

    @ManyToOne
    @JoinColumn(name="ID_MEDICO")
    private UsuarioEntity medico;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ID_PRONTUARIO")
    private ProntuarioEntity prontuario;

    @Column(name="VL_PESO")
    private Float vlPeso;

    @Column(name="VL_ALTURA")
    private Float vlAltura;

    @Column(name="DS_HABITOS_VICIOS")
    private String dsHabitosVicios;

    @Column(name="DS_ALERGIAS_RESTRICOES")
    private String dsAlergiasRestricoes;

    @Column(name="DS_MEDICACAO_USO_CONTINUO")
    private String dsMedicacaoUsoContinuo;

    @Column(name="DS_PROBLEMAS_SAUDE")
    private String dsProblemasSaude;

    @Column(name="DT_ATENDIMENTO")
    private Date dtAtendimento;

}
