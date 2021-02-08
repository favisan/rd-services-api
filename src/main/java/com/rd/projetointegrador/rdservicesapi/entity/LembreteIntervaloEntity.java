package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="TB_LEMBRETE_INTERVALO")
@Data
public class LembreteIntervaloEntity implements Serializable {
    //GRUPO1

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_LEMBRETE_INTERVALO")
    private BigInteger idLembreteIntervalo;

    @Column(name="DS_LEMBRETE_INTERVALO")
    private String dsLembreteIntervalo;

}
