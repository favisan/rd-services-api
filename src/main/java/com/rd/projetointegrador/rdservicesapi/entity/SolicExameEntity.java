package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_SOLIC_EXAME")
public class SolicExameEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SOLIC_EXAME")
    private BigInteger idSolicExame;

    @Column(name = "ID_PRONTUARIO")
    private BigInteger idProntuario;

    @Column(name = "ID_PACIENTE")
    private BigInteger idPaciente;

    @Column(name = "ID_MEDICO")
    private BigInteger idMedico;

    @Column(name = "DT_SOLICITACAO")
    private Date dtSolicitacao;

    @Column(name = "DS_INDICACAO_CLIN")
    private String dsIndicacaoClin;
}
