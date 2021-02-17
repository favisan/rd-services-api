package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.AreaDoCliente;
import com.rd.projetointegrador.rdservicesapi.dto.Lembrete;
import com.rd.projetointegrador.rdservicesapi.dto.LembreteIntervalo;
import com.rd.projetointegrador.rdservicesapi.dto.Usuario;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.LembreteRepository;
import com.rd.projetointegrador.rdservicesapi.repository.LembreteIntervaloRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LembreteService {
    //GRUPO1

    @Autowired private LembreteRepository repository;
    @Autowired private LembreteIntervaloRepository lirepository;
    @Autowired private UsuarioRepository usuarioRepository;

    SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    //MÉTODO: conversão de DTO para Entity
    public LembreteEntity conversaoLembreteEntity(Lembrete lembrete, LembreteEntity lembreteEntity) {

        Optional<LembreteIntervaloEntity> optional = lirepository.findById(lembrete.getLembreteIntervalo().getIdLembreteIntervalo());
        LembreteIntervaloEntity liEntity = optional.get();

        try {
            lembreteEntity.setIdPaciente(lembrete.getIdPaciente());
            lembreteEntity.setLembreteIntervalo(liEntity);
            lembreteEntity.setNmTitulo(lembrete.getNmTitulo());
            lembreteEntity.setDsLembrete(lembrete.getDsLembrete());
            Date dataLembrete = SDF.parse(lembrete.getDtLembrete());
            lembreteEntity.setDtLembrete(dataLembrete);
            lembreteEntity.setDtCriacao(java.util.Calendar.getInstance().getTime());
            lembreteEntity.setHrHora(lembrete.getHrHora());
            lembreteEntity.setNrRepeticao(lembrete.getNrRepeticao());

            return lembreteEntity;
        } catch(ParseException e) {
            System.out.println(e.getMessage());
        }

        return null;
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

        String dtLembrete = SDF.format(lembreteEntity.getDtLembrete());
        lembrete.setDtLembrete(dtLembrete);
        String dtCriacao = SDF.format(lembreteEntity.getDtCriacao());
        lembrete.setDtCriacao(dtCriacao);

        lembrete.setHrHora(lembreteEntity.getHrHora());
        lembrete.setNrRepeticao(lembreteEntity.getNrRepeticao());

        int difData = lembreteEntity.getDtLembrete().compareTo(java.util.Calendar.getInstance().getTime());

        if (difData >= 0) {
            lembrete.setVencido(false);
        }else {
            lembrete.setVencido(true);
        }

        //possibilidade passar a string:
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
    public LembreteEntity getLembrete(BigInteger idLembrete) {
        LembreteEntity lembrete = repository.findById(idLembrete).get();
        return lembrete;
    }
    public List<LembreteEntity> getLembretes() {
        List<LembreteEntity> lembretes = repository.findAll();
        return lembretes;
    }

    //MÉTODOS RETORNANDO A DTO
    public Lembrete getLembreteDTO(BigInteger idLembrete) {
        LembreteEntity lembreteEntity = getLembrete(idLembrete);

        //criação DTO
        Lembrete lembrete = new Lembrete();
        lembrete = conversaoLembreteDTO(lembreteEntity, lembrete);

        return lembrete;
    }
    public List<Lembrete> getLembretesDTO() {
        List<LembreteEntity> lembretesEntities = getLembretes();
        List<Lembrete> lembretes = new ArrayList<>();

        lembretes = conversaoLembretesDTO(lembretesEntities, lembretes);

        return lembretes;
    }
    public List<Lembrete> getLembretesIdPaciente(BigInteger idPaciente) {
        List<LembreteEntity> lembretesEntities = repository.findByIdPaciente(idPaciente);
        List<Lembrete> lembretes = new ArrayList<>();

        lembretes = conversaoLembretesDTO(lembretesEntities, lembretes);

        return lembretes;
    }
    public List<Lembrete> getLembretesOrderByDataAsc(BigInteger idPaciente) {
        List<LembreteEntity> lembretesEntities = repository.findByIdPacienteOrderByDtLembreteAsc(idPaciente);
        List<Lembrete> lembretesPorData = new ArrayList<>();

        lembretesPorData = conversaoLembretesDTO(lembretesEntities, lembretesPorData);

        return lembretesPorData;
    }
    public List<Lembrete> getLembretesOrderByDataDesc(BigInteger idPaciente) {
        List<LembreteEntity> lembretesEntities = repository.findByIdPacienteOrderByDtLembreteDesc(idPaciente);
        List<Lembrete> lembretesPorData = new ArrayList<>();

        lembretesPorData = conversaoLembretesDTO(lembretesEntities, lembretesPorData);

        return lembretesPorData;
    }
    public List<Lembrete> getLembretesOrderByDataCriacao(BigInteger idPaciente) {
        List<LembreteEntity> lembretesEntities = repository.findByIdPacienteOrderByDtCriacaoDesc(idPaciente);
        List<Lembrete> lembretesPorData = new ArrayList<>();

        lembretesPorData = conversaoLembretesDTO(lembretesEntities, lembretesPorData);

        return lembretesPorData;
    }

    @Transactional
    public Boolean cadastrarLembrete(Lembrete lembrete){

        LembreteEntity lembreteEntity = new LembreteEntity();
        BigInteger idPaciente = lembrete.getIdPaciente();

        if(usuarioRepository.existsById(idPaciente)) {
            lembreteEntity = conversaoLembreteEntity(lembrete, lembreteEntity);
            repository.save(lembreteEntity);
            return true;
        }
            return false;
    }

    @Transactional
    public String alterarLembrete(Lembrete lembrete, BigInteger idLembrete){

        Optional<LembreteEntity> optional = repository.findById(idLembrete);
        LembreteEntity lembreteEntity = optional.get();

        lembreteEntity = conversaoLembreteEntity(lembrete, lembreteEntity);

        repository.save(lembreteEntity);
        return "Alteração realizada com sucesso";
    }

    public Boolean excluirLembrete(BigInteger idLembrete){
        repository.deleteById(idLembrete);
        return true;
    }

    //LEMBRETE INTERVALO
    //méotodo retornando a DTO
    public List<LembreteIntervalo> getLembreteIntervalos() {
        List<LembreteIntervaloEntity> lembretesIntervalosEntities = lirepository.findAll();

        List<LembreteIntervalo> lembretesIntervalos = new ArrayList<>();

        for (LembreteIntervaloEntity liEntity: lembretesIntervalosEntities) {
            LembreteIntervalo li = new LembreteIntervalo();
            li.setIdLembreteIntervalo(liEntity.getIdLembreteIntervalo());
            li.setDsLembreteIntervalo(liEntity.getDsLembreteIntervalo());

            lembretesIntervalos.add(li);
        }

        return lembretesIntervalos;

    }
}
