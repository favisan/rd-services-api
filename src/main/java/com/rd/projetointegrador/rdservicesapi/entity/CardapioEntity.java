package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;


@Entity
@Table(name = "TB_CARDAPIO")
@Data
public class CardapioEntity implements Serializable {

   @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
   @Column (name = "ID_CARDAPIO")
   private BigInteger idCardapio;

   // @ManyToOne
   // @JoinColumn (name = "ID_PACIENTE")
   // private IdPaciente idPaciente;
    //ID_PACIENTE              BIGINT (FK) (TB_USUARIO.ID_USUARIO COM TIPO PACIENTE)

    @Column (name = "ID_PACIENTE")
    private BigInteger idPaciente;

    @Column (name = "ID_MEDICO")
    private BigInteger idMedico;


    // @ManyToOne
    // @JoinColumn (name = "ID_MEDICO")
    // private IdMedico idMedico;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_REFEICAO")
    private TipoRefeEntity idTipoRefeicao;


    @Column (name = "NM_NOME_RECEITA")
    private String nomeReceita;

    @Column (name = "QT_CALORIAS")
    private Float qtCalorias;

    @Column (name = "QT_RENDIMENTO")
    private Float qtRendimento;

  @Column (name = "DS_DESCRICAO")
   private String dsDescricao;

}
