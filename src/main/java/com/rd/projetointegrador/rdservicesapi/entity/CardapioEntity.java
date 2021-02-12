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

    @ManyToOne
   @JoinColumn(name="ID_PACIENTE")
    private UsuarioEntity paciente;

    @ManyToOne
    @JoinColumn(name="ID_MEDICO")
    private UsuarioEntity medico;

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
