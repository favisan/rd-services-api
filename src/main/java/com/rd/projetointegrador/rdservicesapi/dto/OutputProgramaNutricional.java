package com.rd.projetointegrador.rdservicesapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputProgramaNutricional {


    private String nome;

    private Float vlPeso;

    private Float vlAltura;

    private String dsHabitosVicios;

    private String dsAlergiasRestricoes;

    private String dsObjetivo;
}
