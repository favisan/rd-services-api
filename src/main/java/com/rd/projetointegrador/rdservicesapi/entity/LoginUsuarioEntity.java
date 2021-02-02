package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "TB_LOGIN_USUARIO")
@Data
public class LoginUsuarioEntity {

    @Id
    @Column(name = "ID_USUARIO")
    private BigInteger idUsuario;

    @Column(name = "DS_SENHA")
    private String dsSenha;

    @Column(name = "DS_EMAIL")
    private String dsEmail;
}