package com.rd.projetointegrador.rdservicesapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Data
@Table(name="TB_LOGIN_USUARIO")
public class LoginUsuarioEntity implements Serializable {
    //GRUPO1

    @Id
    @Column(name="ID_USUARIO")
    private BigInteger idUsuario;

    @PrimaryKeyJoinColumn
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UsuarioEntity usuario;

    @Column(name="DS_SENHA")
    private String dsSenha;

    @Column(name="DS_EMAIL")
    private String dsEmail;

}