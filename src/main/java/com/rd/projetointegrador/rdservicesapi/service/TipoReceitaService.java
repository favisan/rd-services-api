package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.*;
import com.rd.projetointegrador.rdservicesapi.entity.PrescricaoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.ReceituarioEntity;
import com.rd.projetointegrador.rdservicesapi.entity.TipoReceitaEntity;
import com.rd.projetointegrador.rdservicesapi.repository.TipoReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoReceitaService {

    @Autowired
    private TipoReceitaRepository tipoReceitaRepository;

    public List<TipoReceitaEntity> listarTiposDeReceita() {

        List<TipoReceitaEntity> tiposDeReceitaEntity = tipoReceitaRepository.findAll();

        return tiposDeReceitaEntity;
    }

    //Convertendo de Entity para DTO
    public TipoReceita converterTipoReceitaDTO(TipoReceitaEntity tipoReceitaEntity, TipoReceita tipoReceita) {

        //SETANDO OS VALORES NA DTO TipoReceita
        tipoReceita.setIdTipoReceita(tipoReceitaEntity.getIdTipoReceita());
        tipoReceita.setDsTipoReceita(tipoReceitaEntity.getDsTipoReceita());

        return tipoReceita;
    }

    //Convertendo listaEntity para ListaDTO
    public List<TipoReceita> converterTiposReceitaDTO(List<TipoReceitaEntity> tiposReceitaEntity, List<TipoReceita> tiposReceita) {

        for(TipoReceitaEntity TipoReceitaEntity : tiposReceitaEntity) {
            TipoReceita tipoReceita = new TipoReceita();
            receituario = converterReceituarioDTO(receituarioEntity,receituario);

            receituarios.add(receituario);
        }

        return receituarios;
    }

}
