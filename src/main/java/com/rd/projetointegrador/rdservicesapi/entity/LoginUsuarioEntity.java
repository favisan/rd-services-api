package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Data
@Table(name="TB_LOGIN_USUARIO")
public class LoginUsuarioEntity {

    @Id
   @OneToOne
   @JoinColumn (name="ID_USUARIO")
   private UsuarioEntity usuario;

    @Column(name="DS_SENHA")
    private String dsSenha;

    @Column(name="DS_EMAIL")
    private String dsEmail;

}
