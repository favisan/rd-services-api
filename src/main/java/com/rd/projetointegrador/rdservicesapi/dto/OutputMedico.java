package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputMedico {

    private BigInteger idUsuario;

    private Uf ufCrm;

    private EspMed IdEspMed;

    private Preco preco;

    private String nome;

    private String nrCpf;

    private Date dtNascimento;

    private String nrCrm;

    private List<Endereco> enderecos;

    private List<Contato> contatos;
}
