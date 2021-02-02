package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_AG_PACIENTE")
@Data
public class AgPacienteEntity implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID_AG_PACIENTE")
        private BigInteger idAgPaciente;

        @Column(name = "ID_PACIENTE")
        private BigInteger idPaciente;

        @Column(name = "ID_AGENDA")
        private BigInteger idAgenda;

        @Column(name = "DT_SOLICITACAO")
        private LocalDateTime dtSolicitacao;

        @Column(name = "ID_TIPO_CONFIRMACAO")
        private BigInteger idTipoConfirmacao;

        @Column(name = "ID_STATUS_CONSULTA")
        private BigInteger idStatusConsulta;

}
