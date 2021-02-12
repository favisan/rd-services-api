package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.entity.AgendaEntity;
import com.rd.projetointegrador.rdservicesapi.entity.EspMedEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.AgendaRepository;
import com.rd.projetointegrador.rdservicesapi.repository.TipoConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.rd.projetointegrador.rdservicesapi.dto.Agenda;
import com.rd.projetointegrador.rdservicesapi.entity.PeriodoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.TipoConsultaEntity;
import com.rd.projetointegrador.rdservicesapi.repository.PeriodoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;

import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;

@Service
public class AgendaService {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private AgendaRepository agendaRepository;
    @Autowired private TipoConsultaRepository tipoConsultaRepository;
    @Autowired private PeriodoRepository periodoRepository;

    public Set<EspMedEntity> getEspByAgenda() {
        List<AgendaEntity> agendas = agendaRepository.findAll();
        List<UsuarioEntity> usuarios = new ArrayList<>();
        for (AgendaEntity agendaEntity : agendas) {
            UsuarioEntity usuario = agendaEntity.getMedico();
            usuarios.add(usuario);
        }
        Set<EspMedEntity> especialidades = new HashSet<>();
        for (UsuarioEntity usuarioEntity : usuarios) {
            EspMedEntity especialidade = usuarioEntity.getEspMed();
            if (especialidade != null) {
                especialidades.add(especialidade);
            }
        }
        return especialidades;
    }
    public List<AgendaEntity> getAgendaByEspecialidade(BigInteger idEsp, BigInteger idConsulta) {
        List<AgendaEntity> agendaPorTipoConsulta = new ArrayList<>();
        if(idConsulta.intValue() == 2){
            agendaPorTipoConsulta = agendaRepository.findByTipoConsulta(tipoConsultaRepository.findByIdTipoConsulta(idConsulta).get());
        } else {
            agendaPorTipoConsulta = agendaRepository.findAll();
        }
        List<AgendaEntity> agendaFinal = new ArrayList<>();
        for (AgendaEntity agenda : agendaPorTipoConsulta) {
            BigInteger espMedAgenda = agenda.getMedico().getEspMed().getIdEspMed();
            Integer disponibilidade = agenda.getDisponibilidade();
            if (espMedAgenda.equals(idEsp) && disponibilidade==1 ) {
                agendaFinal.add(agenda);
            }
        }
        return agendaFinal;
    }

    public List<AgendaEntity> getAgendas() {
        return agendaRepository.findAll();
    }

    public List<Agenda> getAgendasPorData(Date diaDisponivel) throws ParseException {
        List<Agenda> agendas = new ArrayList<>();

        agendas = agendaRepository.findByDiaDisponivel(diaDisponivel);

        return agendas;
    }

    @Transactional
    public String cadastrarAgenda(Agenda agenda) {
        AgendaEntity agendaEntity = new AgendaEntity();

        BigInteger medico = agenda.getMedico().getIdUsuario();
        UsuarioEntity usuarioEntity = usuarioRepository.findById(medico).get();

        BigInteger tipoConsulta = agenda.getTipoConsulta().getIdTipoConsulta();
        TipoConsultaEntity tipoConsultaEntity = tipoConsultaRepository.findById(tipoConsulta).get();

        BigInteger periodo = agenda.getPeriodo().getIdPeriodo();
        PeriodoEntity periodoEntity = periodoRepository.findById(periodo).get();

        agendaEntity.setMedico(usuarioEntity);
        agendaEntity.setTipoConsulta(tipoConsultaEntity);
        agendaEntity.setPeriodo(periodoEntity);
        agendaEntity.setData(agenda.getData());
//        agendaEntity.setHoraInicial(agenda.getHoraInicial());
//        agendaEntity.setHoraFinal(agenda.getHoraFinal());
        agendaEntity.setDisponibilidade(1);

        agendaRepository.save(agendaEntity);

        return "Agenda cadastrada com sucesso!";
    }

    @Transactional
    public String cadastrarAgendaPorDia(List<Agenda> agendas) throws ParseException {

        for(Agenda agenda: agendas) {
            AgendaEntity agendaEntity = new AgendaEntity();
            List<Agenda> agendasPorData = getAgendasPorData(agenda.getData());

            if(agendasPorData != null) {
                excluirAgendas(agendasPorData);

                BigInteger medico = agenda.getMedico().getIdUsuario();
                UsuarioEntity usuarioEntity = usuarioRepository.findById(medico).get();

                BigInteger periodo = agenda.getPeriodo().getIdPeriodo();
                PeriodoEntity periodoEntity = periodoRepository.findById(periodo).get();

                agendaEntity.setMedico(usuarioEntity);
                agendaEntity.setPeriodo(periodoEntity);
                agendaEntity.setData(agenda.getData());
                agendaEntity.setDisponibilidade(1);

                agendaRepository.save(agendaEntity);

                return "Alteração realizada com sucesso!";
            }

            BigInteger medico = agenda.getMedico().getIdUsuario();
            UsuarioEntity usuarioEntity = usuarioRepository.findById(medico).get();

            BigInteger periodo = agenda.getPeriodo().getIdPeriodo();
            PeriodoEntity periodoEntity = periodoRepository.findById(periodo).get();

            agendaEntity.setMedico(usuarioEntity);
            agendaEntity.setPeriodo(periodoEntity);
            agendaEntity.setData(agenda.getData());
            agendaEntity.setDisponibilidade(1);

            agendaRepository.save(agendaEntity);
        }
        return "Cadastro realizado com sucesso!";
    }

    public String excluirAgendas(List<Agenda> agendas) {

        for(Agenda agenda: agendas) {
            BigInteger id = agenda.getIdAgenda();
            Integer flag = agenda.getDisponibilidade();

            if(!flag.equals(2) && !flag.equals(3)) {
                agendaRepository.deleteById(id);
            }
        }
        return "Operação realizada com sucesso!";
    }

}
