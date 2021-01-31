package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Lembrete;
import com.rd.projetointegrador.rdservicesapi.dto.LembreteIntervalo;
import com.rd.projetointegrador.rdservicesapi.entity.LembreteEntity;
import com.rd.projetointegrador.rdservicesapi.entity.LembreteIntervaloEntity;
import com.rd.projetointegrador.rdservicesapi.repository.LembreteRepository;
import com.rd.projetointegrador.rdservicesapi.repository.LembreteIntervaloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LembreteService {

    @Autowired
    private LembreteRepository repository;
    @Autowired
    private LembreteIntervaloRepository lirepository;

    public Lembrete getLembrete(BigInteger idLembrete) {
        System.out.println("IdLembrete: " + idLembrete);
        Optional<LembreteEntity> optional = repository.findById(idLembrete);
        LembreteEntity lembreteEntity = optional.get();
        LembreteIntervaloEntity liEntity = lembreteEntity.getLembreteIntervalo();

        //criação DTO
        Lembrete lembrete = new Lembrete();
        LembreteIntervalo li = new LembreteIntervalo();

        //passando para DTO
        li.setIdLembreteIntervalo(liEntity.getIdLembreteIntervalo());
        li.setDsLembreteIntervalo(liEntity.getDsLembreteIntervalo());

        lembrete.setIdLembrete(lembreteEntity.getIdLembrete());
        lembrete.setIdPaciente(lembreteEntity.getIdPaciente());
        lembrete.setLembreteIntervalo(li);
        lembrete.setNmTitulo(lembreteEntity.getNmTitulo());
        lembrete.setDsLembrete(lembreteEntity.getDsLembrete());
        lembrete.setDtLembrete(lembreteEntity.getDtLembrete());
        lembrete.setDtCriacao(lembreteEntity.getDtCriacao());
        lembrete.setHrHora(lembreteEntity.getHrHora());
        lembrete.setNrRepeticao(lembreteEntity.getNrRepeticao());
        //lembrete.setLembreteIntervalo(lembreteEntity.getLembreteIntervalo().getDsLembreteIntervalo());

        return lembrete;

    }

    public List<Lembrete> getLembretes(BigInteger idLembrete) {
        List<LembreteEntity> lembretesEntities = repository.findAll();
        List<Lembrete> lembretes = new ArrayList<>();

        //passando para DTO
        for (LembreteEntity lembreteEntity : lembretesEntities) {
            LembreteIntervaloEntity liEntity = lembreteEntity.getLembreteIntervalo();

            Lembrete lembrete = new Lembrete();
            LembreteIntervalo li = new LembreteIntervalo();

            li.setIdLembreteIntervalo(liEntity.getIdLembreteIntervalo());
            li.setDsLembreteIntervalo(liEntity.getDsLembreteIntervalo());

            lembrete.setIdLembrete(lembreteEntity.getIdLembrete());
            lembrete.setIdPaciente(lembreteEntity.getIdPaciente());
            lembrete.setLembreteIntervalo(li);
            lembrete.setNmTitulo(lembreteEntity.getNmTitulo());
            lembrete.setDsLembrete(lembreteEntity.getDsLembrete());
            lembrete.setDtLembrete(lembreteEntity.getDtLembrete());
            lembrete.setDtCriacao(lembreteEntity.getDtCriacao());
            lembrete.setHrHora(lembreteEntity.getHrHora());
            lembrete.setNrRepeticao(lembreteEntity.getNrRepeticao());

            lembretes.add(lembrete);
        }

        return lembretes;

    }

    @Transactional
    public String cadastrarLembrete(Lembrete lembrete){

        LembreteEntity lembreteEntity = new LembreteEntity();
        Optional<LembreteIntervaloEntity> optional = lirepository.findById(lembrete.getLembreteIntervalo().getIdLembreteIntervalo());
        LembreteIntervaloEntity liEntity = optional.get();

        lembreteEntity.setIdPaciente(lembrete.getIdPaciente());
        lembreteEntity.setLembreteIntervalo(liEntity);
        lembreteEntity.setNmTitulo(lembrete.getNmTitulo());
        lembreteEntity.setDsLembrete(lembrete.getDsLembrete());
        lembreteEntity.setDtLembrete(lembrete.getDtLembrete());
        lembreteEntity.setDtCriacao(lembrete.getDtCriacao());
        lembreteEntity.setHrHora(lembrete.getHrHora());
        lembreteEntity.setNrRepeticao(lembrete.getNrRepeticao());

        repository.save(lembreteEntity);

        return "Lembrete cadastrado com sucesso";

    }

    @Transactional
    public String alterarLembrete(Lembrete lembrete, BigInteger idLembrete){

        Optional<LembreteEntity> optional = repository.findById(idLembrete);
        LembreteEntity lembreteEntity = optional.get();

        //TODO: atributos na entity

        lembreteEntity = repository.save(lembreteEntity);
        return "Alteração realizada com sucesso";
    }

    public String excluirLembrete(BigInteger idLembrete){
        repository.deleteById(idLembrete);
        return "Exclusão de Lembrete realizada com sucesso";

    }
}
