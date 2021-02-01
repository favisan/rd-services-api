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

@Table (name="TB_CARTAO")
@Data
public class CartaoEntity implements  Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CARTAO")
    private BigInteger idCartao;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private UsuarioEntity idUsuario;

    @Column(name ="NR_CARTAO")
    private String nrCartao;

    @Column(name ="NR_COD_SEGURANCA")
    private String codSeguranca;

    @Column(name ="DT_VALIDADE")
    private Date dtValidade;

    @Column(name ="DT_EMISSAO")
    private Date dtEmissao;


    @Column (name ="ID_PACIENTE")//FK TB_USUARIO. ID_USUARIO COM TIPO CLIENTE
    private BigInteger IdPaciente;
}
