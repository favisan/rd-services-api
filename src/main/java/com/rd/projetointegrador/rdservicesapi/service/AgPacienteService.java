package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.AgPacienteRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Optional;


@Service
public class AgPacienteService {

    @Autowired private AgPacienteRepository repository;
    @Autowired private UsuarioRepository usuarioRepository;

    public Optional<AgPacienteEntity> getAgPaciente(BigInteger idUsuario){
        usuarioRepository.findByIdUsuario(idUsuario);
        Optional<AgPacienteEntity> listaAgendas = repository.findByPaciente(usuarioRepository.findByIdUsuario(idUsuario).get());
        return listaAgendas;
    };

    @Transactional
    public String setAgPaciente (AgPacienteEntity agPacienteEntity){
        StatusConsultaEntity status = new StatusConsultaEntity();
        status.setIdStatusConsulta(BigInteger.valueOf(1));
//        LocalDateTime data = new LocalDateTime();

        agPacienteEntity.setAgenda(agPacienteEntity.getAgenda());
        agPacienteEntity.setPaciente(agPacienteEntity.getPaciente());
//        agPacienteEntity.setDtSolicitacao(data);
        agPacienteEntity.setTipoConfirmacao(agPacienteEntity.getTipoConfirmacao());
        agPacienteEntity.setStatusConsulta(status);
        agPacienteEntity.getAgenda().setDisponibilidade(2);

        repository.save(agPacienteEntity);

        return "Consulta agendada com sucesso!";
    }

    @Transactional
    public String cancelarAgPaciente(BigInteger idAgPaciente){
        StatusConsultaEntity status = new StatusConsultaEntity();
        status.setIdStatusConsulta(BigInteger.valueOf(3));
        AgPacienteEntity agPaciente = repository.findByIdAgPaciente(idAgPaciente).get();
        agPaciente.setStatusConsulta(status);
        agPaciente.getAgenda().setDisponibilidade(3);

        return "Consulta cancelada com sucesso";
    }

}
