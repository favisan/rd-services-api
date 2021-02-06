package com.rd.projetointegrador.rdservicesapi.dto;

import com.rd.projetointegrador.rdservicesapi.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceituarioOutput {

    private List<TipoReceitaEntity> listaTipoReceita;

    private String nomePaciente;

    private List<MedicacaoEntity> listaMedicacao;

    private List<ViaAdmEntity> listaViaAdm;

    private List<FormaFarmacEntity> listaFormaFarmac;

    private OutputMedico medico;

}
