package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.*;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Time;
import java.util.*;

import org.springframework.transaction.annotation.Transactional;


@Service
public class AgendaService {

    //Repository
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private AgendaRepository agendaRepository;
    @Autowired private TipoConsultaRepository tipoConsultaRepository;
    @Autowired private PeriodoRepository periodoRepository;
    @Autowired private AgPacienteRepository agPacienteRepository;

    //Service
    @Autowired private PeriodoService periodoService;

    //Grupo 2
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

    //Grupo 2
    public List<AgendaEntity> getAgendaByEspecialidade(BigInteger idEsp, BigInteger idConsulta) {
        List<AgendaEntity> agendaPorTipoConsulta = new ArrayList<>();
        if (idConsulta.intValue() == 2) {
            agendaPorTipoConsulta = agendaRepository.findByTipoConsulta(tipoConsultaRepository.findByIdTipoConsulta(idConsulta).get());
        } else {
            agendaPorTipoConsulta = agendaRepository.findAll();
        }
        List<AgendaEntity> agendaFinal = new ArrayList<>();
        for (AgendaEntity agenda : agendaPorTipoConsulta) {
            BigInteger espMedAgenda = agenda.getMedico().getEspMed().getIdEspMed();
            if (espMedAgenda.equals(idEsp)) {
                agendaFinal.add(agenda);
            }
        }
        return agendaFinal;
    }


    //Listar todas as agendas (Grupo 4)
    public List<AgendaEntity> getAgendas() {

        return agendaRepository.findAll();
    }

    //Listar horários por data (Grupo 4)
    public List<Time> getHorarios(Date data) {

        List<AgendaEntity> agendas = agendaRepository.findByData(data);

        List<Time> horarios = new ArrayList<>();

        if (agendas.size() == 0) {
            List<Periodo> periodos = periodoService.listarPeriodos();

            for (Periodo periodo : periodos) {
                horarios.add(periodo.getHoraInicial());
            }

            return horarios;
        }

        for (AgendaEntity agenda : agendas) {
            horarios.add(agenda.getPeriodo().getHoraInicial());
        }

        return horarios;
    }

    public List<AgendaEntity> getAgendasPorData(Date data) {

        //Pegando todas as agendas por data
        List<AgendaEntity> agendas = agendaRepository.findByData(data);

        return agendas;
    }

    //Listar agendas com disponibilidade 2 por data (Grupo 4)
    public List<AgendaEntity> getAgendasPorDataDisponibilidade(Date data) {

        //Pegando todas as agendas do dia com disponibilidade 2
        List<AgendaEntity> agendas = agendaRepository.findByDataAndDisponibilidade(data, 2);

        return agendas;
    }

    //Listar Agendamentos por agenda (Grupo 4)
    public List<AgPacienteEntity> getAgendamentosPorAgenda(Date data, BigInteger idMedico) {
        List<AgPacienteEntity> agendamentos = new ArrayList<>();

        //Pegandos todas as agendas do dia com disponibilidade 2
        List<AgendaEntity> agendas = getAgendasPorDataDisponibilidade(data);

        //Pegandos todas as agendas do dia com disponibilidade 2 por idMedico
        for (AgendaEntity agenda : agendas){
            BigInteger idMedicoAgenda = agenda.getMedico().getIdUsuario();
            if (idMedicoAgenda.equals(idMedico)) {
                AgPacienteEntity agendamento = agPacienteRepository.findByAgenda(agenda);
                agendamentos.add(agendamento);
            }
        }

        return agendamentos;
    }

    //Cadastrar uma agenda (Grupo 4)
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
        agendaEntity.setDisponibilidade(1);

        agendaRepository.save(agendaEntity);

        return "Agenda cadastrada com sucesso!";
    }

    //Cadastrar lista de agendas (Grupo 4)
    @Transactional
    public String cadastrarAgendaPorDia(Date data, List<Agenda> agendasNova) {

        //a data tem que ser capturada do front e a partir dela que esse array de agendas será retornado
        List<AgendaEntity> agendasPorData = getAgendasPorData(data);
        if (agendasPorData.size() > 0) {

            //converter entity para dto
            List<Agenda> agendaDTO = new ArrayList<>();

            excluirAgendas(converterAgendasToDTO(agendasPorData, agendaDTO));

            for (Agenda agenda : agendasNova) {
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

                agendaEntity.setData(data);
                agendaEntity.setDisponibilidade(agenda.getDisponibilidade());

                agendaRepository.save(agendaEntity);
            }
        } else {
            for (Agenda agenda : agendasNova) {
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

                agendaEntity.setData(data);
                agendaEntity.setDisponibilidade(agenda.getDisponibilidade());

                agendaRepository.save(agendaEntity);
            }
        }
        return "Cadastro realizado com sucesso!";
    }

    //Excluir lista de agendas (Grupo 4)
    public String excluirAgendas(List<Agenda> agendas) {

        for (Agenda agenda : agendas) {

            BigInteger id = agenda.getIdAgenda();
            Integer flag = agenda.getDisponibilidade();

            if (!flag.equals(2) && !flag.equals(3) && !flag.equals(4)) {
                agendaRepository.deleteById(id);
            }
        }
        return "Operação realizada com sucesso!";
    }

    //Alterar status do AgPaciente para cancelada e a disponibilidade da agenda para 3 (Grupo 4)
    public String alterarStatusAgPaciente(BigInteger idAgPaciente) {

        AgPacienteEntity agPacienteEntity = agPacienteRepository.findById(idAgPaciente).get();
        StatusConsultaEntity status = new StatusConsultaEntity();
        status.setIdStatusConsulta(BigInteger.valueOf(3));
        agPacienteEntity.setStatusConsulta(status);
        agPacienteEntity.getAgenda().setDisponibilidade(3);

        agPacienteEntity = agPacienteRepository.save(agPacienteEntity);

        return "Agendamento alterado com sucesso";
    }

    //Convertendo de Entity para DTO (Grupo 4)
    public Agenda converterAgendaToDTO(AgendaEntity agendaEntity, Agenda agenda) {

        //PEGAR A DTO InputMedico medico
        InputMedico medico = new InputMedico();
        medico.setIdUsuario(agendaEntity.getMedico().getIdUsuario());
        medico.setNome(agendaEntity.getMedico().getNmNome());
        medico.setNome(agendaEntity.getMedico().getNrCrm());

        //PEGAR A DTO Tipo de consulta
        TipoConsulta tipoConsulta = new TipoConsulta();
        tipoConsulta.setIdTipoConsulta(agendaEntity.getTipoConsulta().getIdTipoConsulta());
        tipoConsulta.setDsTipoConsulta(agendaEntity.getTipoConsulta().getDsTipoConsulta());

        //PEGAR A DTO Periodo
        Periodo periodo = new Periodo();
        periodo.setIdPeriodo(agendaEntity.getPeriodo().getIdPeriodo());
        periodo.setDsPerido(agendaEntity.getPeriodo().getDsPeriodo());
        periodo.setHoraInicial(agendaEntity.getPeriodo().getHoraInicial());

        //SETANDO OS VALORES NA DTO Agenda
        agenda.setIdAgenda(agendaEntity.getIdAgenda());
        agenda.setMedico(medico);
        agenda.setTipoConsulta(tipoConsulta);
        agenda.setPeriodo(periodo);
        agenda.setData(agendaEntity.getData());
        agenda.setDisponibilidade(agendaEntity.getDisponibilidade());

        return agenda;
    }

    //Convertendo listaEntity para ListaDTO (Grupo 4)
    public List<Agenda> converterAgendasToDTO(List<AgendaEntity> agendasEntity, List<Agenda> agendas) {

        for (AgendaEntity agendaEntity : agendasEntity) {
            Agenda agenda = new Agenda();
            agenda = converterAgendaToDTO(agendaEntity, agenda);

            agendas.add(agenda);
        }

        return agendas;
    }

    //Convertendo de DTO para Entity (Grupo 4)
    public AgendaEntity converterAgendaToEntity(Agenda agenda, AgendaEntity agendaEntity) {

        //PEGAR A ENTITY USUARIO Medico
        BigInteger idMedico = agenda.getMedico().getIdUsuario();
        UsuarioEntity medicoEntity = usuarioRepository.findById(idMedico).get();

        //PEGAR A ENTITY Tipo de consulta
        BigInteger idTipoConsulta = agenda.getTipoConsulta().getIdTipoConsulta();
        TipoConsultaEntity tipoConsultaEntity = tipoConsultaRepository.findById(idTipoConsulta).get();

        //PEGAR A ENTITY Periodo
        BigInteger idPeriodo = agenda.getPeriodo().getIdPeriodo();
        PeriodoEntity periodoEntity = periodoRepository.findById(idPeriodo).get();

        //SETANDO OS VALORES NA ENTITY Agenda
        //receituarioEntity.setPaciente(pacienteEntity);
        agendaEntity.setMedico(medicoEntity);
        agendaEntity.setTipoConsulta(tipoConsultaEntity);
        agendaEntity.setPeriodo(periodoEntity);
        agendaEntity.setData(agenda.getData());
        agendaEntity.setDisponibilidade(agenda.getDisponibilidade());

        return agendaEntity;
    }

}