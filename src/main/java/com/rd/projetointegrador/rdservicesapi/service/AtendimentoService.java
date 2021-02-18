package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.*;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.AgPacienteRepository;
import com.rd.projetointegrador.rdservicesapi.repository.AtendimentoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Service
public class AtendimentoService {

    //Repository
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private AtendimentoRepository repository;
    @Autowired private AgPacienteRepository agPacienteRepository;

    //Service
    @Autowired private ProntuarioService prontuarioService;
    @Autowired private UsuarioService usuarioService;
    @Autowired private MedicoService medicoService;
    @Autowired private AgPacienteService agPacienteService;


    //Buscando todos os atendimentos
    public List<Atendimento> listarAtendimentos() {

        List<AtendimentoEntity> atendimentoEntity = repository.findAll();

        List<Atendimento> atendimentos = new ArrayList<>();
        atendimentos = converterAtendimentosToDTO(atendimentoEntity, atendimentos);

        return atendimentos;
    }

    //Buscando atendimento por id
    public Atendimento buscarAtendimentoId(BigInteger id) {

        AtendimentoEntity atendEntity = repository.findById(id).get();

        Atendimento atendimento = new Atendimento();
        atendimento= converterAtendimentoToDTO(atendEntity, atendimento);

        return atendimento;
    }

    //Buscando atendimentos por médico
    public List<Atendimento> consultarPorIdMedico(BigInteger id){

        UsuarioEntity medico = usuarioRepository.findById(id).get();

        List<AtendimentoEntity>  atendData = repository.findByMedicoOrderByDtAtendimentoDesc(medico);

        List<Atendimento> atendimentos = new ArrayList<>();
        atendimentos = converterAtendimentosToDTO(atendData, atendimentos);

        return atendimentos;
    }

    //Buscando atendimentos por cpf de paciente
    public List<Atendimento> consultarPorCpf(String cpf){

        UsuarioEntity paciente = usuarioRepository.findOneByNrCpf(cpf);

        List<AtendimentoEntity> atendPacOrdem = repository.findByPacienteOrderByDtAtendimentoDesc(paciente);

        List<Atendimento> atendimentos = new ArrayList<>();
        atendimentos = converterAtendimentosToDTO(atendPacOrdem, atendimentos);

        return atendimentos;
    }

    //Calcular idade
    public  Integer calcularIdade(final Date dtNascimento) {
        LocalDate dataNascimento = dtNascimento.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
        final LocalDate dataAtual = LocalDate.now();
        final Period periodo = Period.between(dataNascimento, dataAtual);
        return periodo.getYears();
    }

    //Preenchendo a tela Atendimento com os dados que são fixos na tela
    public AtendimentoOutput preencherAtendimento(BigInteger idAgPaciente){

        AgPacienteEntity agPacienteEntity = agPacienteRepository.findById(idAgPaciente).get();

        Date dtNascimento = agPacienteEntity.getPaciente().getDtNascimento();
        Integer idade  = calcularIdade(dtNascimento);

        AtendimentoOutput atendimentoOutput = new AtendimentoOutput();
        atendimentoOutput.setData(agPacienteEntity.getAgenda().getData());
        atendimentoOutput.setIdPaciente(agPacienteEntity.getPaciente().getIdUsuario());
        atendimentoOutput.setNomePaciente(agPacienteEntity.getPaciente().getNmNome());
        atendimentoOutput.setIdade(idade);
        atendimentoOutput.setIdAgPaciente(idAgPaciente);
        atendimentoOutput.setGenero(agPacienteEntity.getPaciente().getGenero().getDsGenero());

        return atendimentoOutput;

    }

    //Cadastrando atendimento
    @Transactional
    public Boolean cadastrarAtendimento(Atendimento atendimento) {

        AtendimentoEntity atendimentoEntity = new AtendimentoEntity();

        atendimentoEntity.setDtAtendimento(atendimento.getDtAtendimento());
        atendimentoEntity.setDsAlergiasRestricoes(atendimento.getDsAlergiasRestricoes());
        atendimentoEntity.setDsMedicacaoUsoContinuo(atendimento.getDsMedicacaoUsoContinuo());
        atendimentoEntity.setDsProblemasSaude(atendimento.getDsProblemasSaude());
        atendimentoEntity.setDsHabitosVicios(atendimento.getDsHabitosVicios());
        atendimentoEntity.setVlAltura(atendimento.getVlAltura());
        atendimentoEntity.setVlPeso(atendimento.getVlPeso());

        UsuarioEntity paciente = usuarioRepository.findById(atendimento.getPaciente().getIdUsuario()).get();
        atendimentoEntity.setPaciente(paciente);

        UsuarioEntity medico = usuarioRepository.findById(atendimento.getMedico().getIdUsuario()).get();
        atendimentoEntity.setMedico(medico);

        ProntuarioEntity prontuarioEntity = prontuarioService.conversaoProntuarioEntity(atendimento.getProntuario());
        atendimentoEntity.setProntuario(prontuarioEntity);

        BigInteger idAgPaciente = atendimento.getAgPaciente().getIdAgPaciente();
        AgPacienteEntity agPacienteEntity = agPacienteRepository.findById(idAgPaciente).get();
        atendimentoEntity.setAgPaciente(agPacienteEntity);

        repository.save(atendimentoEntity);

        alterarStatusAgPaciente(idAgPaciente);

        return true;
    }

    //Alterar status do AgPaciente para realizada e a disponibilidade da agenda para 4 (Grupo 4)
    @Transactional
    public void alterarStatusAgPaciente(BigInteger idAgPaciente) {

        AgPacienteEntity agPacienteEntity = agPacienteRepository.findById(idAgPaciente).get();
        StatusConsultaEntity status = new StatusConsultaEntity();
        status.setIdStatusConsulta(BigInteger.valueOf(2));
        agPacienteEntity.setStatusConsulta(status);
        agPacienteEntity.getAgenda().setDisponibilidade(4);

        agPacienteEntity = agPacienteRepository.save(agPacienteEntity);

    }

    //Convertendo de Entity para DTO
    public Atendimento converterAtendimentoToDTO(AtendimentoEntity atendimentoEntity, Atendimento atendimento) {

        //PEGAR A DTO Usuario paciente
        Usuario paciente = new Usuario();
        paciente = usuarioService.conversaoUsuarioDTO(atendimentoEntity.getPaciente(), paciente);

        //PEGAR A DTO OutputMedico medico
        OutputMedico medico = new OutputMedico();
        medico = medicoService.conversaoOutputMedicoDTO(atendimentoEntity.getMedico(), medico);

        //PEGAR A DTO Prontuario
        Prontuario prontuario = new Prontuario();
        prontuario.setIdProntuario(atendimentoEntity.getProntuario().getIdProntuario());
        prontuario.setDsSubjetivo(atendimentoEntity.getProntuario().getDsSubjetivo());
        prontuario.setDsObjetivo(atendimentoEntity.getProntuario().getDsObjetivo());
        prontuario.setDsAvaliacao(atendimentoEntity.getProntuario().getDsAvaliacao());
        prontuario.setDsPlano(atendimentoEntity.getProntuario().getDsPlano());
        prontuario.setDsObservacoes(atendimentoEntity.getProntuario().getDsObservacoes());


        //SETANDO OS VALORES NA DTO Atendimento
        atendimento.setIdAtendimento(atendimentoEntity.getIdAtendimento());
        atendimento.setPaciente(paciente);
        atendimento.setMedico(medico);
        atendimento.setProntuario(prontuario);
        atendimento.setVlPeso(atendimentoEntity.getVlPeso());
        atendimento.setVlAltura(atendimentoEntity.getVlAltura());
        atendimento.setDsHabitosVicios(atendimentoEntity.getDsHabitosVicios());
        atendimento.setDsAlergiasRestricoes(atendimentoEntity.getDsAlergiasRestricoes());
        atendimento.setDsMedicacaoUsoContinuo(atendimentoEntity.getDsMedicacaoUsoContinuo());
        atendimento.setDsProblemasSaude(atendimentoEntity.getDsProblemasSaude());
        atendimento.setDtAtendimento(atendimentoEntity.getDtAtendimento());

        return atendimento;
    }

    //Convertendo listaEntity para ListaDTO
    public List<Atendimento> converterAtendimentosToDTO(List<AtendimentoEntity> atendimentosEntity, List<Atendimento> atendimentos) {

        for(AtendimentoEntity atendimentoEntity : atendimentosEntity) {
            Atendimento atendimento = new Atendimento();
            atendimento = converterAtendimentoToDTO(atendimentoEntity, atendimento);

            atendimentos.add(atendimento);
        }

        return atendimentos;
    }

}