package com.rd.projetointegrador.rdservicesapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceituarioInput {

    private BigInteger idReceituario;

    private Usuario paciente;

    private Prontuario prontuario;

    private OutputMedico medico;

    private TipoReceita tipoReceita;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dtEmissao;

    private String dsEndImgAssMed;

    private List<Prescricao> prescricoes;

}
