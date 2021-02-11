package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Atendimento;
import com.rd.projetointegrador.rdservicesapi.dto.Prontuario;
import com.rd.projetointegrador.rdservicesapi.entity.AtendimentoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.ProntuarioEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.AtendimentoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioG4Repository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AtendimentoService {

    @Autowired private UsuarioRepository usuarioRepository;

    @Autowired private UsuarioG4Repository usuarioG4Repository;

    @Autowired private ProntuarioService prontuarioService;

    @Autowired private AtendimentoRepository repository;

    public Atendimento buscarAtendimentoId(BigInteger id) {
        System.out.println("ID: " + id);

        AtendimentoEntity entity = repository.findById(id).get();

        Atendimento a = new Atendimento();

        a.setIdAtendimento(entity.getIdAtendimento());
        a.setVlPeso(entity.getVlPeso());
        a.setVlAltura(entity.getVlAltura());
        a.setDsHabitosVicios(entity.getDsHabitosVicios());
        a.setDsAlergiasRestricoes(entity.getDsAlergiasRestricoes());
        a.setDsMedicacaoUsoContinuo(entity.getDsMedicacaoUsoContinuo());
        a.setDsProblemasSaude(entity.getDsProblemasSaude());
        a.setDtAtendimento(entity.getDtAtendimento());

        Prontuario prontuario = new Prontuario();
        prontuario.setIdProntuario(entity.getProntuario().getIdProntuario());

        a.setProntuario(prontuario);

        return a;

    }

    public List<Atendimento> listarAtendimentos() {
        List<AtendimentoEntity> atendimentoEntity = repository.findAll();
        List<Atendimento> atendimentos = new ArrayList<>();

        for (AtendimentoEntity entity : atendimentoEntity) {
            Atendimento a = new Atendimento();

            a.setIdAtendimento(entity.getIdAtendimento());
            a.setVlPeso(entity.getVlPeso());
            a.setVlAltura(entity.getVlAltura());
            a.setDsHabitosVicios(entity.getDsHabitosVicios());
            a.setDsAlergiasRestricoes(entity.getDsAlergiasRestricoes());
            a.setDsMedicacaoUsoContinuo(entity.getDsMedicacaoUsoContinuo());
            a.setDsProblemasSaude(entity.getDsProblemasSaude());
            a.setDtAtendimento(entity.getDtAtendimento());

            Prontuario prontuario = new Prontuario();
            prontuario.setIdProntuario(entity.getProntuario().getIdProntuario());

            a.setProntuario(prontuario);
            atendimentos.add(a);
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

        Optional<UsuarioEntity> user = usuarioRepository.findById(BigInteger.valueOf(1l));
        UsuarioEntity usuario = user.get();
        entity.setMedico(usuario);
        entity.setPaciente(usuario);

        ProntuarioEntity p = prontuarioService.cadastrarProntuario(atendimento.getProntuario());
        entity.setProntuario(p);

        repository.save(entity);

        return "Cadastro realizado com sucesso!";
    }

    public List<AtendimentoEntity> consultarPorCpf(String cpf){

        UsuarioEntity paciente = usuarioG4Repository.findByNrCpf(cpf);
        List<AtendimentoEntity> atendimentos = repository.findByPaciente(paciente);

        return atendimentos;
    }

}