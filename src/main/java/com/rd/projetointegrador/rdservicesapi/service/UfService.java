package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Genero;
import com.rd.projetointegrador.rdservicesapi.dto.Uf;
import com.rd.projetointegrador.rdservicesapi.entity.ContratoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.GeneroEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UfEntity;
import com.rd.projetointegrador.rdservicesapi.repository.UfRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class UfService {

    @Autowired
    private UfRepository repository;

    //MÉTODO: conversão de Entity para DTO
    public Uf conversaoUfDTO(UfEntity ufEntity, Uf uf) {

        uf.setIdUf(ufEntity.getIdUf());
        uf.setDsUf(ufEntity.getDsUf());

        return uf;
    }
    //MÉTODO: conversão listaEntity para ListaDTO
    public List<Uf> conversaoUfDTO(List<UfEntity> ufEntities, List<Uf> ufs){
        for(UfEntity ufEntity : ufEntities){
            Uf uf = new Uf();
            uf= conversaoUfDTO(ufEntity,uf);
            ufs.add(uf);
        }
        return ufs;
    }
    //MÉTODO: conversão de DTO para Entity
    public UfEntity conversaoUfEntity(Uf uf, UfEntity ufEntity) {
        ufEntity.setIdUf(uf.getIdUf());
        ufEntity.setDsUf(uf.getDsUf());

        return ufEntity;

    }
    //MÉTODO: conversão ListaDTO para ListaEntity
    public List<UfEntity> conversaoUfsEntities(List<Uf> ufs,List<UfEntity> ufsEntities){
        for(Uf uf : ufs){
            UfEntity ufEntity= new UfEntity();
            ufEntity= conversaoUfEntity(uf,ufEntity);
            ufsEntities.add(ufEntity);
        }
        return ufsEntities;
    }
    public List<UfEntity> getUfs() {
        return repository.findAll();
    }
     public List<Uf> getUfsDTO() {
        List<UfEntity> ufEntities = getUfs();
        List<Uf> ufs = new ArrayList<>();

        ufs= conversaoUfDTO(ufEntities, ufs);

        return ufs;
    }
}

