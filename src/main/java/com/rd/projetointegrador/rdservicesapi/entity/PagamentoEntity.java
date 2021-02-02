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
@Table(name="TB_PAGAMENTO")
@Data
public class PagamentoEntity  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PAGAMENTO")
    private BigInteger  idPagamento;

    @Column (name="ID_CARTAO")
    private BigInteger idCartao ;

    @Column  (name= "ID_CONTRATO")
    private BigInteger idContrato;

    @Column (name= "ID_FORMA_PAGAMENTO")
    private BigInteger idFormaPgt;

    @Column (name= "ID_NF")
    private BigInteger idNF;

    @Column  (name= "ID_AG_PACIENTE")
    private BigInteger idAgPaciente;

    @Column  (name= "ID_PEDIDO")
    private BigInteger idPedido;

    @Column (name= "VL_PAGAMENTO")
    private Double vlPagamento;

    @Column (name= "DT_PAGAMENTO")
    private Date dtPagamento;

    @Column (name= "NR_PARCELA")
    private Integer nrParcela;
}
