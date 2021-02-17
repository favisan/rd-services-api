package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.Data;
import java.math.BigInteger;

//Grupo2 - DTO criada para o serviço setAgPaciente
@Data
public class CadastroAgPaciente {
    private BigInteger idAgenda;
    private BigInteger idUsuario;
}
