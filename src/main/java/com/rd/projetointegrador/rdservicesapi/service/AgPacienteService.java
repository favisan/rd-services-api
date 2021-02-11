package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.*;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.AgPacienteRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AgPacienteService {

    @Autowired private AgPacienteRepository repository;
    @Autowired private UsuarioRepository usuarioRepository;

    public List<AgPaciente> getAgPaciente(Optional<UsuarioEntity> usuario){
        List<AgPacienteEntity> listaAgendas = repository.findByPaciente(usuario.get());
        List<AgPaciente> agPacientes = new ArrayList<>();

        for (AgPacienteEntity agPacienteEntity : listaAgendas){
            AgPaciente agPaciente = new AgPaciente();
            agPaciente.setIdAgPaciente(agPacienteEntity.getIdAgPaciente());

            //idAgenda
            Agenda agenda = new Agenda();
            agenda.setIdAgenda(agPacienteEntity.getAgenda().getIdAgenda());

            //nomeMedico
            UsuarioEntity medicoEntity = agPacienteEntity.getAgenda().getMedico();
            InputMedico medicoDto = new InputMedico();
            medicoDto.setNome(medicoEntity.getNmNome());

            //especialidade
            EspMedEntity espMedEntity = agPacienteEntity.getAgenda().getMedico().getEspMed();
            EspMed espMedDto = new EspMed();
            espMedDto.setIdEspMed(espMedEntity.getIdEspMed());
            espMedDto.setDsEspMed(espMedEntity.getDsEspMed());
            medicoDto.setEspMed(espMedDto);
            agenda.setMedico(medicoDto);

            //data
            agenda.setDiaDisponivel(agPacienteEntity.getAgenda().getDiaDisponivel());

            //horaInicial
            agenda.setHoraInicial(agPacienteEntity.getAgenda().getPeriodo().getHoraInicial());

            //statusConsulta
            StatusConsulta status = new StatusConsulta();
            status.setIdStatusConsulta(agPacienteEntity.getStatusConsulta().getIdStatusConsulta());
            status.setDsStatusConsulta(agPacienteEntity.getStatusConsulta().getDsStatusConsulta());
            agPaciente.setStatusConsulta(status);

            //passando agendaDTO para agPacienteDTO
            agPaciente.setAgenda(agenda);

            agPacientes.add(agPaciente);
        }

        return agPacientes;
    };

    @Transactional
    public String setAgPaciente (AgPacienteEntity agPacienteEntity){
        StatusConsultaEntity status = new StatusConsultaEntity();
        status.setIdStatusConsulta(BigInteger.valueOf(1));
        LocalDateTime data = LocalDateTime.now();

        agPacienteEntity.setAgenda(agPacienteEntity.getAgenda());
        agPacienteEntity.setPaciente(agPacienteEntity.getPaciente());
        agPacienteEntity.setDtSolicitacao(data);
        agPacienteEntity.setTipoConfirmacao(agPacienteEntity.getTipoConfirmacao());
        agPacienteEntity.setStatusConsulta(status);

        repository.save(agPacienteEntity);

        return "Consulta agendada com sucesso!";
    }

    @Transactional
    public String cancelarAgPaciente(BigInteger idAgPaciente){
        StatusConsultaEntity status = new StatusConsultaEntity();
        status.setIdStatusConsulta(BigInteger.valueOf(3));
        AgPacienteEntity agPaciente = repository.findByIdAgPaciente(idAgPaciente).get();
        agPaciente.setStatusConsulta(status);

        return "Consulta cancelada com sucesso";
    }

}
