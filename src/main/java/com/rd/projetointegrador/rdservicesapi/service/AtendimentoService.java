package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Atendimento;
import com.rd.projetointegrador.rdservicesapi.dto.Prontuario;
import com.rd.projetointegrador.rdservicesapi.dto.Usuario;
import com.rd.projetointegrador.rdservicesapi.entity.AtendimentoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.ProntuarioEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.AtendimentoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AtendimentoService {

    @Autowired private UsuarioRepository usuarioRepository;

    @Autowired private ProntuarioService prontuarioService;

    @Autowired private AtendimentoRepository repository;

    public Atendimento buscarAtendimentoId(BigInteger id) {
        System.out.println("ID: " + id);

        AtendimentoEntity entity = repository.findById(id).get();

        Atendimento atend = new Atendimento();


        atend.setIdAtendimento(entity.getIdAtendimento());
        atend.setVlPeso(entity.getVlPeso());
        atend.setVlAltura(entity.getVlAltura());
        atend.setDsHabitosVicios(entity.getDsHabitosVicios());
        atend.setDsAlergiasRestricoes(entity.getDsAlergiasRestricoes());
        atend.setDsMedicacaoUsoContinuo(entity.getDsMedicacaoUsoContinuo());
        atend.setDsProblemasSaude(entity.getDsProblemasSaude());
        atend.setDtAtendimento(entity.getDtAtendimento());


        Prontuario prontuario = new Prontuario();
        prontuario.setIdProntuario(entity.getProntuario().getIdProntuario());

        atend.setProntuario(prontuario);

        return atend;

    }

    public List<Atendimento> listarAtendimentos() {
        List<AtendimentoEntity> atendimentoEntity = repository.findAll();
        List<Atendimento> atendimentos = new ArrayList<>();

        for (AtendimentoEntity entity : atendimentoEntity) {
            Atendimento atend = new Atendimento();

            atend.setIdAtendimento(entity.getIdAtendimento());
            atend.setVlPeso(entity.getVlPeso());
            atend.setVlAltura(entity.getVlAltura());
            atend.setDsHabitosVicios(entity.getDsHabitosVicios());
            atend.setDsAlergiasRestricoes(entity.getDsAlergiasRestricoes());
            atend.setDsMedicacaoUsoContinuo(entity.getDsMedicacaoUsoContinuo());
            atend.setDsProblemasSaude(entity.getDsProblemasSaude());
            atend.setDtAtendimento(entity.getDtAtendimento());

            Prontuario prontuario = new Prontuario();
            prontuario = prontuarioService.conversaoProntuarioDto(entity.getProntuario());
            atend.setProntuario(prontuario);
            atendimentos.add(atend);
        }
        return atendimentos;

    }

    @Transactional
    public String cadastrarAtendimento(Atendimento atendimento) {
        AtendimentoEntity entity = new AtendimentoEntity();
        entity.setDtAtendimento(atendimento.getDtAtendimento());
        entity.setDsAlergiasRestricoes(atendimento.getDsAlergiasRestricoes());
        entity.setDsMedicacaoUsoContinuo(atendimento.getDsMedicacaoUsoContinuo());
        entity.setDsProblemasSaude(atendimento.getDsProblemasSaude());
        entity.setDsHabitosVicios(atendimento.getDsHabitosVicios());
        entity.setVlAltura(atendimento.getVlAltura());
        entity.setVlPeso(atendimento.getVlPeso());

        UsuarioEntity paciente = usuarioRepository.findById(atendimento.getPaciente().getIdUsuario()).get();
        UsuarioEntity medico = usuarioRepository.findById(atendimento.getMedico().getIdUsuario()).get();

        entity.setMedico(medico);
        entity.setPaciente(paciente);

        ProntuarioEntity p = prontuarioService.conversaoProntuarioEntity(atendimento.getProntuario());
        entity.setProntuario(p);

        repository.save(entity);

        return "Cadastro realizado com sucesso!";
    }



    public List<Atendimento> consultarPorIdMedico(BigInteger id){

        UsuarioEntity medico = usuarioRepository.findById(id).get();
        List<AtendimentoEntity>  atendData = repository.findByMedicoOrderByDtAtendimentoDesc(medico);
        List<Atendimento> atendList = converterEntityToDTO(atendData);

        return atendList;
    }

    private List<Atendimento> converterEntityToDTO(List<AtendimentoEntity> atendimentoEntities){
        List<Atendimento> atendimentosDto = new ArrayList<>();

        for(AtendimentoEntity atendimentoEntity : atendimentoEntities){
            Atendimento atendimentoDto = new Atendimento();
            atendimentoDto.setDtAtendimento(atendimentoEntity.getDtAtendimento());
            atendimentoDto.setDsProblemasSaude(atendimentoEntity.getDsProblemasSaude());
            atendimentoDto.setDsMedicacaoUsoContinuo(atendimentoEntity.getDsMedicacaoUsoContinuo());
            atendimentoDto.setDsAlergiasRestricoes(atendimentoEntity.getDsAlergiasRestricoes());
            atendimentoDto.setDsHabitosVicios(atendimentoEntity.getDsHabitosVicios());
            atendimentoDto.setVlAltura(atendimentoEntity.getVlAltura());
            atendimentoDto.setVlPeso(atendimentoEntity.getVlPeso());


           Prontuario prontuario = prontuarioService.conversaoProntuarioDto(atendimentoEntity.getProntuario());

            atendimentoDto.setProntuario(prontuario);
            atendimentosDto.add(atendimentoDto);

        }
        return atendimentosDto;
    }

    public List<AtendimentoEntity> consultarPorCpf(String cpf){

        UsuarioEntity paciente = usuarioRepository.findOneByNrCpf(cpf);
        List<AtendimentoEntity> atendimentos = repository.findByPaciente(paciente);

        return atendimentos;
    }

//    @Transactional
//    public String alterarStatusAgPaciente(BigInteger idAgPaciente){
//        StatusConsultaEntity status = new StatusConsultaEntity();
//        status.setIdStatusConsulta(BigInteger.valueOf(2));
//        AgPacienteEntity agPaciente = repository.findById(idAgPaciente).get();
//        agPaciente.setStatusConsulta(status);
//        agPacienteEntity = agPacienteRepository.save(agPacienteEntity);
//
//        return "Consulta Realizada";
//    }
}
