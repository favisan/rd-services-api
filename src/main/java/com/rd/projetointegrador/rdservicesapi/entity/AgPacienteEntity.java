package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "TB_AG_PACIENTE")
@Data
public class AgPacienteEntity implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID_AG_PACIENTE")
        private BigInteger idAgPaciente;

        @ManyToOne
        @JoinColumn(name = "ID_USUARIO")
        private UsuarioEntity paciente;

        @OneToOne
        @JoinColumn(name = "ID_AGENDA")
        private AgendaEntity agenda;

        @Column(name = "DT_SOLICITACAO")
        private Date dtSolicitacao;

        @ManyToOne
        @JoinColumn (name = "ID_TIPO_CONFIRMACAO")
        private TipoConfirmacaoEntity tipoConfirmacao;

        @ManyToOne
        @JoinColumn(name = "ID_STATUS_CONSULTA")
        private StatusConsultaEntity statusConsulta;

}
