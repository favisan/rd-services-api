package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoInput {
    private BigInteger idUsuario;
    private List<AgServico> agendamentos;
    private Cartao cartao;
}
