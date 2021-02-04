package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginUsuario {
    private BigInteger idUsuario;
    private String dsSenha;
    private String dsEmail;
}
