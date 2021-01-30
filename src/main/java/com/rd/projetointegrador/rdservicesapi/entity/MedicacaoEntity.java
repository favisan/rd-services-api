package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name="TB_MEDICACAO")
@Data
public class MedicacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_MEDICACAO")
    private BigInteger idMedicacao;

    @Column(name="DS_MEDICACAO")
    private String dsMedicacao;

//    @ManyToMany(mappedBy = "medicamentos")
//    private List<ReceituarioEntity> receituarios;

}
