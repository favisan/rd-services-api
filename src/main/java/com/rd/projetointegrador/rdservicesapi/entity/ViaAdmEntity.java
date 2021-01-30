package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name="TB_VIA_ADM")
@Data
public class ViaAdmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_VIA_ADM")
    private BigInteger idViaAdm;

    @Column(name="DS_VIA_ADM")
    private String dsViaAdm;

}
