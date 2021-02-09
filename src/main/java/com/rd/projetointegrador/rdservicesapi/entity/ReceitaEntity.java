package br.com.rd.projetointegrador.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "TB_RECEITA")
@Data
public class ReceitaEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID_RECEITA")
    private BigInteger idReceita;

    @Column (name = "NM_NOME_RECEITA")
    private String nomeReceita;

    @Column (name = "DS_RECEITA")
    private String dsReceita;

    @Column (name="QT_CALORIAS")
    private Float qtCalorias;

    @Column (name="QT_RENDIMENTO")
    private Float qtRendimento;

    @Column (name="DS_END_IMAGEM")
    private String endImagem;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TB_RECEITA_CATEGORIA",
            joinColumns = @JoinColumn(name = "ID_RECEITA"),
            inverseJoinColumns = @JoinColumn(name = "ID_CATEGORIA")
    )
    private List<CategoriaEntity> categorias;
}
