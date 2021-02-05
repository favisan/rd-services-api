package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.AgPacienteRepository;
import com.rd.projetointegrador.rdservicesapi.repository.AgendaRepository;
import com.rd.projetointegrador.rdservicesapi.repository.TipoConfirmacaoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AgPacienteService {

    @Autowired private AgPacienteRepository repository;
    @Autowired private AgendaRepository agendaRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private TipoConfirmacaoRepository tipoConfirmacaoRepository;


    @Transactional
    public String setAgPaciente (BigInteger idAgenda, BigInteger idPaciente, BigInteger idtipoConfirmacao){
        AgPacienteEntity agPacienteEntity = new AgPacienteEntity();
        StatusConsultaEntity status = new StatusConsultaEntity();
        status.setIdStatusConsulta(BigInteger.valueOf(1));

        agPacienteEntity.setAgenda(agendaRepository.findByIdAgenda(idAgenda).get());
        agPacienteEntity.setPaciente(usuarioRepository.findByIdUsuario(idPaciente).get());
        agPacienteEntity.setTipoConfirmacao(tipoConfirmacaoRepository.findByIdTipoConfirmacao(idtipoConfirmacao).get());
        agPacienteEntity.setStatusConsulta(status);

        repository.save(agPacienteEntity);

        return "Consulta agendada com sucesso!";
    }

    public List<AgPacienteEntity> getAgPaciente(){
        List<AgPacienteEntity> listaAgendas = repository.findAll();
        return listaAgendas;
    };

    @Transactional
    public String cancelarAgPaciente(BigInteger idAgPaciente){
        StatusConsultaEntity status = new StatusConsultaEntity();
        status.setIdStatusConsulta(BigInteger.valueOf(3));
        AgPacienteEntity agPaciente = repository.findByIdAgPaciente(idAgPaciente).get();
        agPaciente.setStatusConsulta(status);

        return "Consulta cancelada com sucesso";
    }

}
