package com.rd.projetointegrador.rdservicesapi.dto;

import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class LoginUsuario {
    //GRUPO1

        private BigInteger idUsuario;
        private String dsSenha;
        private String dsEmail;

    }

