package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceituarioOutput {

    private List<TipoReceita> listaTipoReceita;

    private String nomePaciente;

    private BigInteger idProntuario;

    private List<Medicacao> listaMedicacao;

    private List<ViaAdm> listaViaAdm;

    private List<FormaFarmac> listaFormaFarmac;

    private OutputMedico medico;

}
