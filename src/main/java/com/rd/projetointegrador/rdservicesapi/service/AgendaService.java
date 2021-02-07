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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
        agendaEntity.setDiaDisponivel(agenda.getDiaDisponivel());
//        agendaEntity.setHoraInicial(agenda.getHoraInicial());
//        agendaEntity.setHoraFinal(agenda.getHoraFinal());
        agendaEntity.setFlDisponivel(1);

        agendaRepository.save(agendaEntity);

        return "Agenda cadastrada com sucesso!";
    }

    @Transactional
    public String cadastrarAgendaPorDia(List<Agenda> agendas) throws ParseException {

        for(Agenda agenda: agendas) {
            AgendaEntity agendaEntity = new AgendaEntity();
            List<Agenda> agendasPorData = getAgendasPorData(agenda.getDiaDisponivel());

            if(agendasPorData != null) {
                excluirAgendas(agendasPorData);

                BigInteger medico = agenda.getMedico().getIdUsuario();
                UsuarioEntity usuarioEntity = usuarioRepository.findById(medico).get();

                BigInteger periodo = agenda.getPeriodo().getIdPeriodo();
                PeriodoEntity periodoEntity = periodoRepository.findById(periodo).get();

                agendaEntity.setMedico(usuarioEntity);
                agendaEntity.setPeriodo(periodoEntity);
                agendaEntity.setDiaDisponivel(agenda.getDiaDisponivel());
                agendaEntity.setFlDisponivel(1);

                agendaRepository.save(agendaEntity);

                return "Alteração realizada com sucesso!";
            }

            BigInteger medico = agenda.getMedico().getIdUsuario();
            UsuarioEntity usuarioEntity = usuarioRepository.findById(medico).get();

            BigInteger periodo = agenda.getPeriodo().getIdPeriodo();
            PeriodoEntity periodoEntity = periodoRepository.findById(periodo).get();

            agendaEntity.setMedico(usuarioEntity);
            agendaEntity.setPeriodo(periodoEntity);
            agendaEntity.setDiaDisponivel(agenda.getDiaDisponivel());
            agendaEntity.setFlDisponivel(1);

            agendaRepository.save(agendaEntity);
        }
        return "Cadastro realizado com sucesso!";
    }

    public String excluirAgendas(List<Agenda> agendas) {

        for(Agenda agenda: agendas) {
            BigInteger id = agenda.getIdAgenda();
            Integer flag = agenda.getFlDisponivel();

            if(!flag.equals(2) && !flag.equals(3)) {
                agendaRepository.deleteById(id);
            }
        }
        return "Operação realizada com sucesso!";
    }

}
