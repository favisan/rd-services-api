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

    @Autowired private ProntuarioRepository repository;

    //Buscar prontuário por Id
    public Prontuario buscarProntuarioId(BigInteger id) {
        ProntuarioEntity entity = repository.findById(id).get();
        Prontuario prontuário = new Prontuario();
        prontuário.setIdProntuario(entity.getIdProntuario());
        prontuário.setDsSubjetivo(entity.getDsSubjetivo());
        prontuário.setDsAvaliacao(entity.getDsAvaliacao());
        prontuário.setDsObjetivo(entity.getDsObjetivo());
        prontuário.setDsPlano(entity.getDsPlano());
        prontuário.setDsObservacoes(entity.getDsObservacoes());
        return prontuário;
    }

    //Listar todos os prontuários
    public List<Prontuario> listarProntuarios(){
        List<ProntuarioEntity> prontuarioEntity = repository.findAll();
        List<Prontuario> prontuarios = new ArrayList<>();

        for (ProntuarioEntity entity : prontuarioEntity) {
            Prontuario prontuario = new Prontuario();

            prontuario.setIdProntuario(entity.getIdProntuario());
            prontuario.setDsSubjetivo(entity.getDsSubjetivo());
            prontuario.setDsAvaliacao(entity.getDsAvaliacao());
            prontuario.setDsObjetivo(entity.getDsObjetivo());
            prontuario.setDsPlano(entity.getDsPlano());
            prontuario.setDsObservacoes(entity.getDsObservacoes());

            prontuarios.add(prontuario);
        }
        return prontuarios;
    }

    //Cadastrar prontuário
    public ProntuarioEntity cadastrarProntuario(Prontuario prontuario) {
        ProntuarioEntity entity = new ProntuarioEntity();
        entity.setDsSubjetivo(prontuario.getDsSubjetivo());
        entity.setDsAvaliacao(prontuario.getDsAvaliacao());
        entity.setDsObjetivo(prontuario.getDsObjetivo());
        entity.setDsPlano(prontuario.getDsPlano());
        entity.setDsObservacoes(prontuario.getDsObservacoes());

        ProntuarioEntity prontuarioEntity = repository.save(entity);

        return prontuarioEntity;
    }

    //Conversão de Entity para DTO
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

    //Conversão de DTO para Entity
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
