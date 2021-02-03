package com.rd.projetospring.primeiroprojeto.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "TB_PEDIDO")
@Data
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO")
    private BigInteger idPedido;

    @Column(name = "ID_PACIENTE")
    private BigInteger idPaciente;

    @Column(name = "VL_TOTAL")
    private Float vlTotal;

    //relacionamento Bi-direcional
    @OneToMany(mappedBy = "pedido")
    private List<AgServicoEntity> agendamentos;

}
