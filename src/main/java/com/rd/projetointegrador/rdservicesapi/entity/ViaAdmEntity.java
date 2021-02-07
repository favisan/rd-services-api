package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@NoArgsConstructor
@Table(name="TB_VIA_ADM")
public class ViaAdmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_VIA_ADM")
    private BigInteger idViaAdm;

    @Column(name="DS_VIA_ADM")
    private String dsViaAdm;

}
