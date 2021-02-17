package com.rd.projetointegrador.rdservicesapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rd.projetointegrador.rdservicesapi.entity.LembreteIntervaloEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lembrete {
    //GRUPO1

    private BigInteger idLembrete;
    private BigInteger idPaciente;
    private LembreteIntervalo lembreteIntervalo;
    private String nmTitulo;
    private String dsLembrete;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String dtLembrete;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String dtCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date hrHora;
    private Integer nrRepeticao;
    private Boolean vencido;

}
