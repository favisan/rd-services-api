package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="TB_RECEITUARIO")
@Data
@NoArgsConstructor
public class ReceituarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_RECEITUARIO")
    private BigInteger idReceituario;

    @ManyToOne
    @JoinColumn(name="ID_PACIENTE")
    private UsuarioEntity paciente;

    //receitas de tipos diferentes não podem ser feitas no mesmo receituario
    // logo um prontuário pode gerar mais de 1 receituario
    @ManyToOne
    @JoinColumn(name="ID_PRONTUARIO")
    private ProntuarioEntity prontuario;

    @ManyToOne
    @JoinColumn(name="ID_MEDICO")
    private UsuarioEntity medico;

    @ManyToOne
    @JoinColumn(name="ID_TIPO_RECEITA")
    private TipoReceitaEntity tipoReceita;

    @Column(name="DT_EMISSAO")
    private Date dtEmissao;

    @Column(name="DS_END_IMG_ASS_MED")
    private String dsEndImgAssMed;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TB_RECEITUARIO_PRESCRICAO",
            joinColumns = @JoinColumn(name = "ID_RECEITUARIO"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRESCRICAO")
    )
    private List<PrescricaoEntity> prescricoes;

}
