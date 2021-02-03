package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "TB_TIPO_USUARIO")
@Data
public class TipoUsuarioEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_TIPO_USUARIO")
    private BigInteger idTipoUsuario;

    @Column(name = "DS_TIPO_USUARIO")
    private String dsTipoUsuario;
}