package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Genero;
import com.rd.projetointegrador.rdservicesapi.entity.GeneroEntity;
import com.rd.projetointegrador.rdservicesapi.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository repository;

    //MÉTODO: conversão de DTO para Entity
    public Genero conversaoGeneroDTO(GeneroEntity generoEntity, Genero genero) {
        genero.setIdGenero(generoEntity.getIdGenero());
        genero.setDsGenero(generoEntity.getDsGenero());

        return genero;
    }

    //MÉTODO: conversão de Entity para DTO
    public List<Genero> conversaoGenerosDTO(List<GeneroEntity> generosEntities, List<Genero> generos){
        for(GeneroEntity generoEntity : generosEntities){
            Genero genero = new Genero();
            genero= conversaoGeneroDTO(generoEntity,genero);
            generos.add(genero);
        }
        return generos;
    }

    //MÉTODO: conversão ListaDTO para ListaEntity
    public GeneroEntity conversaoGeneroEntity(Genero genero, GeneroEntity generoEntity) {
        generoEntity.setIdGenero(genero.getIdGenero());
        generoEntity.setDsGenero(genero.getDsGenero());

        return generoEntity;

    }

    //MÉTODO: conversão listaEntity para ListaDTO
    public List<GeneroEntity> conversaoGenerosEntities(List<Genero> generos,List<GeneroEntity> generosEntities){
      for(Genero genero : generos){
          GeneroEntity generoEntity= new GeneroEntity();
          generoEntity= conversaoGeneroEntity(genero,generoEntity);
          generosEntities.add(generoEntity);
      }
        return generosEntities;
    }

    //MÉTODOS RETORNANDO A ENTITY
    public GeneroEntity getGenero(BigInteger idGenero) {
        System.out.println("Id: " + idGenero);
        Optional<GeneroEntity> optional = repository.findById(idGenero);
        return optional.get();
    }
    public List<GeneroEntity> getGeneros() {
        return repository.findAll();
    }

    //MÉTODOS retornando a DTO
    public Genero getGeneroDTO(BigInteger idGenero) {
        GeneroEntity generoEntity = getGenero(idGenero);
        Genero genero = new Genero();

        genero = conversaoGeneroDTO(generoEntity, genero);
        return genero;
    }
    public List<Genero> getGenerosDTO() {
        List<GeneroEntity> generosEntities = getGeneros();
        List<Genero> generos = new ArrayList<>();

        generos = conversaoGenerosDTO(generosEntities, generos);

        return generos;
    }
}

