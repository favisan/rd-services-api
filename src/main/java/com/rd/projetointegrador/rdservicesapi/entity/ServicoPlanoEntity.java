package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
@Data
@Table(name ="TB_SERVICO_PLANO")
public class ServicoPlanoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_SERVICO_PLANO")
    private BigInteger idServicoPlano;

    @Column(name="DS_SERVICO")
    private String dsServico;

}
