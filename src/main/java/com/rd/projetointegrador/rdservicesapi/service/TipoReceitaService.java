package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.entity.TipoReceitaEntity;
import com.rd.projetointegrador.rdservicesapi.repository.TipoReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoReceitaService {

    @Autowired
    private TipoReceitaRepository tipoReceitaRepository;

    public List<TipoReceitaEntity> listarTiposDeReceita() {

        List<TipoReceitaEntity> tiposDeReceitaEntity = tipoReceitaRepository.findAll();

        return tiposDeReceitaEntity;
    }

}
