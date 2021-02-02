package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name="TB_GENERO")
@Data
public class GeneroEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_GENERO")
    private BigInteger idGenero;

    @Column(name = "DS_GENERO")
    private String dsGenero;
}
