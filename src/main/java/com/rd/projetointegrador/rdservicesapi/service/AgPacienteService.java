package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.AgPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Service
public class AgPacienteService {

    @Autowired private AgPacienteRepository repository;

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
