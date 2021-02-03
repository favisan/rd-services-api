package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "TB_LOJA")
@Data
public class LojaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LOJA")
    private BigInteger idLoja;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_LOJA")
    private TipoLojaEntity idTipoLoja;

    @Column(name = "CD_LOJA")
    private BigInteger cdLoja;

    @Column(name = "NM_LOJA")
    private String nmLoja;

    @Column(name = "NR_CNPJ")
    private String nrCnpj;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TB_LOJA_ENDERECO",
            joinColumns = @JoinColumn(name = "ID_LOJA"),
            inverseJoinColumns = @JoinColumn(name = "ID_ENDERECO")
    )
    private List<EnderecoEntity> enderecos;

}
