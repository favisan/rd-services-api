package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.*;
import com.rd.projetointegrador.rdservicesapi.entity.TipoReceitaEntity;
import com.rd.projetointegrador.rdservicesapi.repository.TipoReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoReceitaService {

    //Repository
    @Autowired
    private TipoReceitaRepository tipoReceitaRepository;

    //Listar todos os tipos de receitas
    public List<TipoReceita> listarTiposDeReceita() {

        //Buscando Toda A Lista de Entity TipoReceita
        List<TipoReceitaEntity> tiposReceitaEntity = tipoReceitaRepository.findAll();

        //Convertendo a Lista Entity TipoReceita para Lista DTO
        List<TipoReceita> tiposReceita = new ArrayList<>();
        tiposReceita = converterTiposReceitaDTO(tiposReceitaEntity, tiposReceita);

        return tiposReceita;

    }

    //Convertendo de Entity para DTO
    public TipoReceita converterTipoReceitaDTO(TipoReceitaEntity tipoReceitaEntity, TipoReceita tipoReceita) {

        //SETANDO OS VALORES NA DTO TipoReceita
        tipoReceita.setIdTipoReceita(tipoReceitaEntity.getIdTipoReceita());
        tipoReceita.setDsTipoReceita(tipoReceitaEntity.getDsTipoReceita());

        return tipoReceita;
    }

    //Convertendo lista Entity para ListaDTO
    public List<TipoReceita> converterTiposReceitaDTO(List<TipoReceitaEntity> tiposReceitaEntity, List<TipoReceita> tiposReceita) {

        for(TipoReceitaEntity tipoReceitaEntity : tiposReceitaEntity) {
            TipoReceita tipoReceita = new TipoReceita();
            tipoReceita = converterTipoReceitaDTO(tipoReceitaEntity, tipoReceita);

            tiposReceita.add(tipoReceita);
        }

        return tiposReceita;
    }

}
