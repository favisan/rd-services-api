package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="TB_PLANOS")
@Data
    public class PlanosEntity implements Serializable {
    //GRUPO1

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="ID_PLANO")
        private BigInteger idPlano;

        @Column (name="NM_PLANO")
        private String nmPlano;

        @Column (name= "DS_PLANO")
        private String dsPlano;

        @Column (name= "VL_PLANO")
        private Double vlPlano;

        @Column (name= "ID_SERVICO_PLANO")
        private BigInteger idServicoPlano;

        @ManyToMany(cascade = CascadeType.ALL)
        @Cascade(value=org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
        @JoinTable(name = "TB_PLANOS_SERVPLANOS",
                joinColumns = @JoinColumn(name = "ID_PLANO"),
                inverseJoinColumns = @JoinColumn(name = "ID_SERVICO_PLANO"))
        private List<ServicoPlanoEntity> servicos;

}


