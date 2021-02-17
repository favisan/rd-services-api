package com.rd.projetointegrador.rdservicesapi.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaOutput {
    private Periodo periodo;
    private Integer disponibilidade;
}