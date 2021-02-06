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
public class Receituario {

    private BigInteger idReceituario;

    private InputMedico paciente;

    private Prontuario prontuario;

    private InputMedico medico;

    private TipoReceita tipoReceita;

    private Date dtEmissao;

    private String dsEndImgAssMed;

    private List<Prescricao> prescricoes;

}
