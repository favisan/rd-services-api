package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Prontuario;
import com.rd.projetointegrador.rdservicesapi.entity.ProntuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository repository;

    public Prontuario buscarProntuarioId(BigInteger id) {
        System.out.println("ID: " + id);

        ProntuarioEntity entity = repository.findById(id).get();

        Prontuario p = new Prontuario();


        p.setIdProntuario(entity.getIdProntuario());
        p.setDsSubjetivo(entity.getDsSubjetivo());
        p.setDsAvaliacao(entity.getDsAvaliacao());
        p.setDsObjetivo(entity.getDsObjetivo());
        p.setDsPlano(entity.getDsPlano());
        p.setDsObservacoes(entity.getDsObservacoes());

        return p;

    }

    public List<Prontuario> listarProntuarios(Prontuario prontuario){
        List<ProntuarioEntity> prontuarioEntity = repository.findAll();
        List<Prontuario> prontuarios = new ArrayList<>();

        for (ProntuarioEntity entity : prontuarioEntity) {
            Prontuario p = new Prontuario();

            p.setIdProntuario(entity.getIdProntuario());
            p.setDsSubjetivo(entity.getDsSubjetivo());
            p.setDsAvaliacao(entity.getDsAvaliacao());
            p.setDsObjetivo(entity.getDsObjetivo());
            p.setDsPlano(entity.getDsPlano());
            p.setDsObservacoes(entity.getDsObservacoes());

        }

        return prontuarios;
    }
    public ProntuarioEntity cadastrarProntuario(Prontuario prontuario) {
        ProntuarioEntity entity = new ProntuarioEntity();
        entity.setDsSubjetivo(prontuario.getDsSubjetivo());
        entity.setDsAvaliacao(prontuario.getDsAvaliacao());
        entity.setDsObjetivo(prontuario.getDsObjetivo());
        entity.setDsPlano(prontuario.getDsPlano());
        entity.setDsObservacoes(prontuario.getDsObservacoes());

        ProntuarioEntity p = repository.save(entity);

        return p;
    }

    public Prontuario conversaoProntuarioDto(ProntuarioEntity prontuarioEntity) {

        Prontuario prontuario= new Prontuario();

        prontuario.setIdProntuario(prontuarioEntity.getIdProntuario());
        prontuario.setDsPlano(prontuarioEntity.getDsPlano());
        prontuario.setDsSubjetivo(prontuarioEntity.getDsSubjetivo());
        prontuario.setDsAvaliacao(prontuarioEntity.getDsAvaliacao());
        prontuario.setDsObservacoes(prontuarioEntity.getDsObservacoes());
        prontuario.setDsObjetivo(prontuarioEntity.getDsObjetivo());

        return prontuario;
    }


    public ProntuarioEntity conversaoProntuarioEntity(Prontuario prontuario){

        ProntuarioEntity entity = new ProntuarioEntity();
        entity.setDsSubjetivo(prontuario.getDsSubjetivo());
        entity.setDsAvaliacao(prontuario.getDsAvaliacao());
        entity.setDsObjetivo(prontuario.getDsObjetivo());
        entity.setDsPlano(prontuario.getDsPlano());
        entity.setDsObservacoes(prontuario.getDsObservacoes());

        return entity;
    }

}
