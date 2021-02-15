package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "TB_USUARIO_ENDERECO")
@Data
public class UsuarioEnderecoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private BigInteger idUsuario;

    @Id
    @Column(name = "ID_ENDERECO")
    private BigInteger idEndereco;

}
