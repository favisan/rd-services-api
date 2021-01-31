package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_TIPO_EXAME")
public class TipoExameEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_EXAME")
    private BigInteger idTipoExame;

    @Column(name = "DS_TIPO_EXAME")
    private String dsTipoExame;
}
