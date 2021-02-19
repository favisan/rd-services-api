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
import java.util.Date;



@Service
public class AgendaService {

    //Repository
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private TipoConsultaRepository tipoConsultaRepository;
    @Autowired
    private PeriodoRepository periodoRepository;
    @Autowired
    private AgPacienteRepository agPacienteRepository;

    //Service
    @Autowired
    private PeriodoService periodoService;
    @Autowired
    private MedicoService medicoService;


    //Grupo2 - Lista de agendas médicas disponíveis por especialidade e por tipo de consulta
    public List<Agenda> getAgendaByEspecialidade(BigInteger idEsp, BigInteger idConsulta) {
        List<AgendaEntity> agendaPorTipoConsulta = new ArrayList<>();
        //Se consulta=2, retornar as agendas com status presencial; se consulta=1(online), traz todas as agendas
        if (idConsulta.intValue() == 2) {
            agendaPorTipoConsulta = agendaRepository.findByTipoConsulta(tipoConsultaRepository.findById(idConsulta).get());
        } else {
            agendaPorTipoConsulta = agendaRepository.findAll();
        }
        List<AgendaEntity> agendaFinal = new ArrayList<>();
        List<Agenda> agendasDto = new ArrayList<>();
        for (AgendaEntity agenda : agendaPorTipoConsulta) {
            BigInteger espMedAgenda = agenda.getMedico().getEspMed().getIdEspMed();
            Integer disponibilidade = agenda.getDisponibilidade();
            //filtra pela especialidade escolhida e pela disponibilidade
            if (espMedAgenda.equals(idEsp) && disponibilidade == 1) {
                agendaFinal.add(agenda);
            }
        }
        //convertendo a lista de agendaEntity em agendaDTO
        for (AgendaEntity agendaEntity : agendaFinal) {
            Agenda agendaDto = new Agenda();
            agendaDto.setIdAgenda(agendaEntity.getIdAgenda());
            OutputMedico medicoDto = new OutputMedico();
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

    //Grupo2 - Filtra as agendas disponiveis por data
    public List<Agenda> filtrarAgendasDispPorData (List<Agenda> agendasDisponiveis, Date data){
        List <Agenda> agendasFiltradas = new ArrayList<>();
        for (Agenda agenda : agendasDisponiveis){
            if (agenda.getData().equals(data)){
                agendasFiltradas.add(agenda);
            }
        }
        return agendasFiltradas;
    }

    //Grupo2 - Mudar a disponibilidade da Agenda Médica para agendada
    @Transactional
    public boolean mudarDisponibilidadeParaAgendada(BigInteger idAgenda) {
        AgendaEntity agenda = agendaRepository.findById(idAgenda).get();
        agenda.setDisponibilidade(2);

        return true;
    }

    //--------------------------------------------------------------------------------

    //Listar todas as agendas (Grupo 4)
    public List<Agenda> getAgendas() {
        List<AgendaEntity> agendaEntity = agendaRepository.findAll();
        List<Agenda> agendas = new ArrayList<>();
        agendas = converterAgendasToDTO(agendaEntity, agendas);
        return agendas;
    }

    //Listar horários por data (Grupo 4)
    public List<AgendaOutput> getHorarios(Date data) {
        List<AgendaEntity> agendasEntity = agendaRepository.findByData(data);
        List<Agenda> agendas = new ArrayList<>();
        agendas = converterAgendasToDTO(agendasEntity, agendas);
        List<AgendaOutput> agendasOutput = new ArrayList<>();
        if (agendas.size() == 0) {
            List<Periodo> periodos = periodoService.listarPeriodos();
            for (Periodo periodo : periodos) {
                AgendaOutput agendaOutput = new AgendaOutput();
                agendaOutput.setPeriodo(periodo);
                agendaOutput.setDisponibilidade(2);
                agendaOutput.setIdTipoConsulta(BigInteger.valueOf(1));
                OutputMedico medico = new OutputMedico();
                agendaOutput.setMedico(medico);
                agendasOutput.add(agendaOutput);
            }
            return agendasOutput;
        }
        for (Agenda agenda : agendas) {
            AgendaOutput agendaOutput = new AgendaOutput();
            agendaOutput.setPeriodo(agenda.getPeriodo());
            agendaOutput.setDisponibilidade(agenda.getDisponibilidade());
            agendaOutput.setIdTipoConsulta(agenda.getTipoConsulta().getIdTipoConsulta());
            OutputMedico medico = new OutputMedico();
            agendaOutput.setMedico(medico);
            agendasOutput.add(agendaOutput);
        }
        return agendasOutput;
    }

    //Listar agendas com disponibilidade 2 por data (Grupo 4)
    public List<AgendaEntity> getAgendasPorDataDisponibilidade(Date data) {
        List<AgendaEntity> agendas = agendaRepository.findByDataAndDisponibilidade(data, 2);
        return agendas;
    }

    //Listar Agendamentos por agenda (Grupo 4)
    public List<AgendamentoOutput> getAgendamentosPorAgenda(Date data, BigInteger idMedico) {
        List<AgendamentoOutput> agendamentos = new ArrayList<>();
        //Pegandos todas as agendas do dia com disponibilidade 2
        List<AgendaEntity> agendas = getAgendasPorDataDisponibilidade(data);
        //Pegandos todas as agendas do dia com disponibilidade 2 por idMedico
        for (AgendaEntity agenda : agendas) {
            BigInteger idMedicoAgenda = agenda.getMedico().getIdUsuario();
            if (idMedicoAgenda.equals(idMedico)) {
                AgPacienteEntity agPacienteEntity = agPacienteRepository.findByAgenda(agenda);
                AgendamentoOutput agPaciente = new AgendamentoOutput();
                agPaciente = converterAgPacienteToDTO(agPacienteEntity, agPaciente);
                agendamentos.add(agPaciente);
            }
        }
        return agendamentos;
    }

    //Buscar Agendas por data
    public List<AgendaEntity> getAgendasPorData(Date data) {

        List<AgendaEntity> agendasEntity = agendaRepository.findByData(data);
        return agendasEntity;
    }

    //Cadastrar lista de agendas (Grupo 4)
    @Transactional
    public Boolean cadastrarAgendaPorDia(Date data, List<AgendaOutput> agendasNova) {
        List<AgendaEntity> agendasPorData = new ArrayList<>();
        agendasPorData = getAgendasPorData(data);
        if (agendasPorData.size() > 0) {
            excluirAgendas(agendasPorData);
            for (AgendaOutput agenda : agendasNova) {
                AgendaEntity agendaEntity = new AgendaEntity();
                BigInteger medico = agenda.getMedico().getIdUsuario();
                UsuarioEntity usuarioEntity = usuarioRepository.findById(medico).get();
                BigInteger tipoConsulta = agenda.getIdTipoConsulta();
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
            for (AgendaOutput agenda : agendasNova) {
                AgendaEntity agendaEntity = new AgendaEntity();
                BigInteger medico = agenda.getMedico().getIdUsuario();
                UsuarioEntity usuarioEntity = usuarioRepository.findById(medico).get();
                BigInteger tipoConsulta = agenda.getIdTipoConsulta();
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
        return true;
    }

    //Excluir lista de agendas (Grupo 4)
    public void excluirAgendas(List<AgendaEntity> agendas) {
        for (AgendaEntity agenda : agendas) {
            BigInteger id = agenda.getIdAgenda();
            Integer flag = agenda.getDisponibilidade();
            System.out.println(id);
            System.out.println(flag);
            if (!flag.equals(2) && !flag.equals(3) && !flag.equals(4)) {
                agendaRepository.deleteById(id);
                System.out.println(id);
            }
        }
    }

    //Alterar status do AgPaciente para cancelada e a disponibilidade da agenda para 3 (Grupo 4)
    public Boolean alterarStatusAgPaciente(BigInteger idAgPaciente) {
        AgPacienteEntity agPacienteEntity = agPacienteRepository.findById(idAgPaciente).get();
        StatusConsultaEntity status = new StatusConsultaEntity();
        status.setIdStatusConsulta(BigInteger.valueOf(3));
        agPacienteEntity.setStatusConsulta(status);
        agPacienteEntity.getAgenda().setDisponibilidade(3);
        agPacienteEntity = agPacienteRepository.save(agPacienteEntity);
        return true;
    }

    //Convertendo de Entity para DTO (Grupo 4)
    public Agenda converterAgendaToDTO(AgendaEntity agendaEntity, Agenda agenda) {
        //PEGAR A DTO OutputMedico medico
        OutputMedico medico = new OutputMedico();
        medico = medicoService.conversaoOutputMedicoDTO(agendaEntity.getMedico(), medico);
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

    //Convertendo listaEntity para ListaDTO (Grupo 4)
    public List<AgendaEntity> converterAgendasToEntity(List<Agenda> agendas, List<AgendaEntity> agendasEntity) {
        for (Agenda agenda : agendas) {
            AgendaEntity agendaEntity = new AgendaEntity();
            agendaEntity = converterAgendaToEntity(agenda, agendaEntity);
            agendasEntity.add(agendaEntity);
        }
        return agendasEntity;
    }

    //Convertendo de DTO para Entity (Grupo 4)
    public AgendaEntity converterAgendaOutputToEntity(AgendaOutput agenda, AgendaEntity agendaEntity) {
        //PEGAR A ENTITY USUARIO Medico
        BigInteger idMedico = agenda.getMedico().getIdUsuario();
        UsuarioEntity medicoEntity = usuarioRepository.findById(idMedico).get();
        //PEGAR A ENTITY Tipo de consulta
        BigInteger idTipoConsulta = agenda.getIdTipoConsulta();
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

    //Convertendo AgPaciente de Entity para DTO (Grupo 4)
    public AgendamentoOutput converterAgPacienteToDTO(AgPacienteEntity agPacienteEntity, AgendamentoOutput agPaciente) {
        //SETANDO OS VALORES NA DTO Agenda
        agPaciente.setIdAgPaciente(agPacienteEntity.getIdAgPaciente());
        agPaciente.setPaciente(agPacienteEntity.getPaciente());
        agPaciente.setAgenda(agPacienteEntity.getAgenda());
        agPaciente.setDtSolicitacao(agPacienteEntity.getDtSolicitacao());
        agPaciente.setTipoConfirmacao(agPacienteEntity.getTipoConfirmacao());
        agPaciente.setStatusConsulta(agPacienteEntity.getStatusConsulta());
        return agPaciente;
    }

}