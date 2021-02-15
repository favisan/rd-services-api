package com.rd.projetointegrador.rdservicesapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "TB_AG_SERVICO")
@Data
public class AgServicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AGENDAMENTO")
    private BigInteger idAgendamento;

    @ManyToOne
    @JoinColumn(name = "ID_SERVICO")
    private ServicoEntity idServico;

    @ManyToOne
    @JoinColumn(name = "ID_LOJA")
    private LojaEntity idLoja;

    @ManyToOne
    @JoinColumn(name = "ID_STATUS")
    private StatusEntity idStatus;

    @Column(name = "DT_DATA_HORA")
    private Date dtDataHora;

    //relacionamento Bi-direcional
    @ManyToOne
    @JoinColumn(name = "ID_PEDIDO")
    @JsonIgnore
    private PedidoEntity pedido;


}
