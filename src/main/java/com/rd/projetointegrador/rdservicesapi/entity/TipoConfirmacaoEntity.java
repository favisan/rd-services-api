package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "TB_TIPO_CONFIRMACAO")
@Data
public class TipoConfirmacaoEntity implements Serializable {

    @Id
    @Column(name = "ID_TIPO_CONFIRMACAO")
    private BigInteger idTipoConfirmacao;

    @Column(name = "DS_TIPO_CONFIRMACAO")
    private String dsTipoConfirmacao;
}
