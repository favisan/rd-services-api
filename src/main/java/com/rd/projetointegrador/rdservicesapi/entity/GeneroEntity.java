package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Data
@Table(name="TB_GENERO")
public class GeneroEntity implements Serializable {
    //GRUPO1

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_GENERO")
    private BigInteger idGenero;

    @Column(name="DS_GENERO")
    private String dsGenero;

}
