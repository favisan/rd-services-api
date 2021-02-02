package com.rd.projetointegrador.rdservicesapi.entity;

import com.rd.projetointegrador.rdservicesapi.dto.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

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


    @ManyToOne
    @JoinColumn(name = "ID_PRONTUARIO")
    private ProntuarioEntity prontuario;


    @ManyToOne
    @JoinColumn(name = "ID_PACIENTE")
    private UsuarioEntity paciente;

    @ManyToOne
    @JoinColumn(name = "ID_MEDICO")
    private UsuarioEntity medico;


    @Column(name = "DT_SOLICITACAO")
    private Date dtSolicitacao;


    @Column(name = "DS_INDICACAO_CLIN")
    private String dsIndicacaoClin;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TB_SOLIC_EXAME_TIPO_EXAME",
            joinColumns = @JoinColumn(name = "ID_SOLIC_EXAME"),
            inverseJoinColumns = @JoinColumn(name = "ID_TIPO_EXAME", insertable = false, updatable = false)
    )
    private List<TipoExameEntity> exames;
}
