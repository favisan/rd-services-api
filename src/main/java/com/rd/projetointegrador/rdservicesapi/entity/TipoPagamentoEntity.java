package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;


@Entity
@Data
@Table(name="TB_PAGAMENTO_TIPO")
public class TipoPagamentoEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_FORMA_PAGAMENTO")
    private BigInteger idFormaPagamento;

    @Column (name = "DS_FORMA_PAGAMENTO")
    private String dsFormaPagamento;
}

