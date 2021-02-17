package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.*;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.util.Date;

@Service
public class AgendaService {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private AgendaRepository agendaRepository;
    @Autowired private TipoConsultaRepository tipoConsultaRepository;
    @Autowired private PeriodoRepository periodoRepository;

    //Grupo2 - Lista de agendas médicas disponíveis por especialidade e por tipo de consulta
     public List<Agenda> getAgendaByEspecialidade(BigInteger idEsp, BigInteger idConsulta) {
        List<AgendaEntity> agendaPorTipoConsulta = new ArrayList<>();
        //Se consulta=2, retornar as agendas com status presencial; se consulta=1(online), traz todas as agendas
        if(idConsulta.intValue() == 2){
            agendaPorTipoConsulta = agendaRepository.findByTipoConsulta(tipoConsultaRepository.findById(idConsulta).get());
        } else {
            agendaPorTipoConsulta = agendaRepository.findAll();
        }
        List<AgendaEntity> agendaFinal = new ArrayList<>();
        List<Agenda> agendasDto =  new ArrayList<>();
        for (AgendaEntity agenda : agendaPorTipoConsulta) {
            BigInteger espMedAgenda = agenda.getMedico().getEspMed().getIdEspMed();
            Integer disponibilidade = agenda.getDisponibilidade();
            //filtra pela especialidade escolhida e pela disponibilidade
            if (espMedAgenda.equals(idEsp) && disponibilidade==1 ) {
                agendaFinal.add(agenda);
            }
        }
        //convertendo a lista de agendaEntity em agendaDTO
        for (AgendaEntity agendaEntity : agendaFinal){
            Agenda agendaDto = new Agenda();
            agendaDto.setIdAgenda(agendaEntity.getIdAgenda());
            InputMedico medicoDto = new InputMedico();
            medicoDto.setNome(agendaEntity.getMedico().getNmNome());
            medicoDto.setDsEndImg(agendaEntity.getMedico().getDsEndImg());
            Preco precoDto = new Preco();
            precoDto.setVlConsulta(agendaEntity.getMedico().getPreco().getVlConsulta());
            medicoDto.setPreco(precoDto);
            EspMed espMedDto = new EspMed();
            espMedDto.setDsEspMed(agendaEntity.getMedico().getEspMed().getDsEspMed());
            medicoDto.setEspMed(espMedDto);
            agendaDto.setMedico(medicoDto);
            Periodo periodoDto = new Periodo();
            periodoDto.setHoraInicial(agendaEntity.getPeriodo().getHoraInicial());
            periodoDto.setDsPerido(agendaEntity.getPeriodo().getDsPeriodo());
            agendaDto.setPeriodo(periodoDto);
            agendaDto.setData(agendaEntity.getData());

            agendasDto.add(agendaDto);
        }
        return agendasDto;
    }

    //Grupo2 - Mudar a disponibilidade da Agenda Médica para agendada
    @Transactional
    public boolean mudarDisponibilidadeParaAgendada(BigInteger idAgenda){
        AgendaEntity agenda = agendaRepository.findById(idAgenda).get();
        agenda.setDisponibilidade(2);

        return true;
    }

    public List<AgendaEntity> getAgendas() {
        return agendaRepository.findAll();
    }

    public List<Agenda> getAgendasPorData(Date diaDisponivel) throws ParseException {
        List<Agenda> agendas = new ArrayList<>();

        agendas = agendaRepository.findByData(diaDisponivel);

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
