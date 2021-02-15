package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Uf;
import com.rd.projetointegrador.rdservicesapi.entity.UfEntity;
import com.rd.projetointegrador.rdservicesapi.repository.UfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UfService {

    @Autowired private UfRepository ufRepository;

    //MÉTODO: conversão de DTO para Entity
    public UfEntity conversaoUfEntity(Uf uf, UfEntity ufEntity){

        ufEntity.setIdUf(uf.getIdUf());
        ufEntity.setDsUf(uf.getDsUf());

        return ufEntity;
    }

    //MÉTODO: conversão ListaDTO para ListaEntity
    public List<UfEntity> conversaoUfsEntity(List<Uf> ufs, List<UfEntity> ufsEntities){

        for(Uf uf: ufs) {
            UfEntity ufEntity = new UfEntity();
            ufEntity = conversaoUfEntity(uf, ufEntity);

            ufsEntities.add(ufEntity);
        }
        return ufsEntities;
    }

    //MÉTODO: conversão de Entity para DTO
    public Uf conversaoUfDTO(UfEntity ufEntity, Uf uf){

        uf.setIdUf(ufEntity.getIdUf());
        uf.setDsUf(ufEntity.getDsUf());

        return uf;
    }

    //MÉTODO: conversão listaEntity para ListaDTO
    public List<Uf> conversaoUfsDTO(List<UfEntity> ufsEntities, List<Uf> ufs){
        for(UfEntity ufEntity: ufsEntities) {
            Uf uf = new Uf();
            uf = conversaoUfDTO(ufEntity, uf);

            ufs.add(uf);
        }
        return ufs;
    }

    public List<Uf> getUfsDTO(){
        List<UfEntity> ufsEntities = ufRepository.findAll();
        List<Uf> ufs = new ArrayList<>();

        ufs = conversaoUfsDTO(ufsEntities, ufs);

        return ufs;
    }

}