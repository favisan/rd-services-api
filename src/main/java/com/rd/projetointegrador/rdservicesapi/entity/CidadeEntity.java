package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "TB_CIDADE")
@Data
public class CidadeEntity implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) //add qnd é autoincrement
        @Column(name = "ID_CIDADE")
        private BigInteger idCidade;

        @ManyToOne
        @JoinColumn(name = "ID_UF")
        private UfEntity idUf;

        @Column(name = "CD_CIDADE_IBGE")
        private BigInteger cdCidadeIbge;

        @Column(name = "DS_CIDADE")
        private String dsCidade;
}
