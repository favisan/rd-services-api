package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Lembrete;
import com.rd.projetointegrador.rdservicesapi.dto.LembreteIntervalo;
import com.rd.projetointegrador.rdservicesapi.dto.Usuario;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.LembreteRepository;
import com.rd.projetointegrador.rdservicesapi.repository.LembreteIntervaloRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LembreteService {

    @Autowired private LembreteRepository repository;
    @Autowired private LembreteIntervaloRepository lirepository;
    @Autowired private UsuarioRepository usuarioRepository;

    //MÉTODO: conversão de DTO para Entity
    public LembreteEntity conversaoLembreteEntity(Lembrete lembrete, LembreteEntity lembreteEntity) {

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

        return lembreteEntity;
    }
    //MÉTODO: conversão de Entity para DTO
    public Lembrete conversaoLembreteDTO(LembreteEntity lembreteEntity, Lembrete lembrete) {

        LembreteIntervaloEntity liEntity = lembreteEntity.getLembreteIntervalo();

        //passando para DTO
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
        //lembrete.setLembreteIntervalo(lembreteEntity.getLembreteIntervalo().getDsLembreteIntervalo());

        return lembrete;
    }
    //MÉTODO: conversão ListaDTO para ListaEntity
    public List<LembreteEntity> conversaoLembretesEntity(List<Lembrete> lembretes, List<LembreteEntity> lembretesEntities) {

        for (Lembrete lembrete : lembretes) {
            LembreteEntity lembreteEntity = new LembreteEntity();
            lembreteEntity = conversaoLembreteEntity(lembrete, lembreteEntity);

            lembretesEntities.add(lembreteEntity);
        }

        return lembretesEntities;

    }
    //MÉTODO: conversão listaEntity para ListaDTO
    public List<Lembrete> conversaoLembretesDTO(List<LembreteEntity> lembretesEntities, List<Lembrete> lembretes) {

        for (LembreteEntity lembreteEntity : lembretesEntities) {
            Lembrete lembrete = new Lembrete();
            lembrete = conversaoLembreteDTO(lembreteEntity, lembrete);

            lembretes.add(lembrete);
        }

        return lembretes;
    }


    //MÉTODOS RETORNANDO A ENTITY
    public List<LembreteEntity> getLembretesIdPaciente(BigInteger idPaciente) {
        List<LembreteEntity> lembretes = repository.findByIdPaciente(idPaciente);
        return lembretes;
    }
    public List<LembreteEntity> getLembretesOrderByDataAsc(BigInteger idPaciente) {
        List<LembreteEntity> lembretesPorData = repository.findByIdPacienteOrderByDtLembreteAsc(idPaciente);
        return lembretesPorData;
    }
    public List<LembreteEntity> getLembretesOrderByDataDesc(BigInteger idPaciente) {
        List<LembreteEntity> lembretesPorData = repository.findByIdPacienteOrderByDtLembreteDesc(idPaciente);
        return lembretesPorData;
    }
    public List<LembreteEntity> getLembretesOrderByDataCriacao(BigInteger idPaciente) {
        List<LembreteEntity> lembretesPorData = repository.findByIdPacienteOrderByDtCriacao(idPaciente);
        return lembretesPorData;
    }


    //MÉTODOS RETORNANDO A DTO
    public Lembrete getLembreteDTO(BigInteger idLembrete) {
        Optional<LembreteEntity> optional = repository.findById(idLembrete);
        LembreteEntity lembreteEntity = optional.get();

        //criação DTO
        Lembrete lembrete = new Lembrete();
        lembrete = conversaoLembreteDTO(lembreteEntity, lembrete);

        return lembrete;
    }
    public List<Lembrete> getLembretesDTO(BigInteger idLembrete) {
        List<LembreteEntity> lembretesEntities = repository.findAll();
        List<Lembrete> lembretes = new ArrayList<>();

        lembretes = conversaoLembretesDTO(lembretesEntities, lembretes);

        return lembretes;
    }

    @Transactional
    public String cadastrarLembrete(Lembrete lembrete){

        LembreteEntity lembreteEntity = new LembreteEntity();
        BigInteger idPaciente = lembrete.getIdPaciente();

        if(usuarioRepository.existsById(idPaciente)) {
            lembreteEntity = conversaoLembreteEntity(lembrete, lembreteEntity);
            repository.save(lembreteEntity);
            return "Lembrete cadastrado com sucesso";
        }
            return null;
    }

    @Transactional
    public String alterarLembrete(Lembrete lembrete, BigInteger idLembrete){

        Optional<LembreteEntity> optional = repository.findById(idLembrete);
        LembreteEntity lembreteEntity = optional.get();

        lembreteEntity = conversaoLembreteEntity(lembrete, lembreteEntity);

        repository.save(lembreteEntity);
        return "Alteração realizada com sucesso";
    }

    public String excluirLembrete(BigInteger idLembrete){
        repository.deleteById(idLembrete);
        return "Exclusão de Lembrete realizada com sucesso";

    }
}
