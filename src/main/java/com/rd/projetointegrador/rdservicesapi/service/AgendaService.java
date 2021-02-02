package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Agenda;
import com.rd.projetointegrador.rdservicesapi.entity.AgendaEntity;
import com.rd.projetointegrador.rdservicesapi.entity.PeriodoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.TipoConsultaEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.AgendaRepository;
import com.rd.projetointegrador.rdservicesapi.repository.PeriodoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.TipoConsultaRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
public class AgendaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private TipoConsultaRepository tipoConsultaRepository;

    @Autowired
    private PeriodoRepository periodoRepository;

    public List<AgendaEntity> getAgendas() {
        return agendaRepository.findAll();
    }

    @Transactional
    public String cadastrarAgenda(Agenda agenda) {
        AgendaEntity agendaEntity = new AgendaEntity();

        BigInteger idMedico = agenda.getMedico().getIdUsuario();
        UsuarioEntity usuarioEntity = usuarioRepository.findById(idMedico).get();

        BigInteger idTipoConsulta = agenda.getTipoConsulta().getIdTipoConsulta();
        TipoConsultaEntity tipoConsultaEntity = tipoConsultaRepository.findById(idTipoConsulta).get();

        BigInteger periodo = agenda.getPeriodo().getIdPeriodo();
        PeriodoEntity periodoEntity = periodoRepository.findById(periodo).get();

        agendaEntity.setMedico(usuarioEntity);
        agendaEntity.setIdTipoConsulta(tipoConsultaEntity);
        agendaEntity.setIdPeriodo(periodoEntity);
        agendaEntity.setDiaDisponivel(agenda.getDiaDisponivel());
        agendaEntity.setHoraInicial(agenda.getHoraInicial());
        agendaEntity.setHoraFinal(agenda.getHoraFinal());
        agendaEntity.setFlDisponivel(agenda.getFlDisponivel());

        agendaRepository.save(agendaEntity);

        return "Agenda cadastrada com sucesso!";
    }

}
