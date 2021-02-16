package com.rd.projetointegrador.rdservicesapi.dto;

import com.rd.projetointegrador.rdservicesapi.entity.AgendaEntity;
import com.rd.projetointegrador.rdservicesapi.entity.StatusConsultaEntity;
import com.rd.projetointegrador.rdservicesapi.entity.TipoConfirmacaoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoOutput {

    private BigInteger idAgPaciente;

    private UsuarioEntity paciente;

    private AgendaEntity agenda;

    private LocalDateTime dtSolicitacao;

    private TipoConfirmacaoEntity tipoConfirmacao;

    private StatusConsultaEntity statusConsulta;

}
