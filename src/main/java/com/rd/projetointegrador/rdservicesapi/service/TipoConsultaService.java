package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.entity.TipoConsultaEntity;
import com.rd.projetointegrador.rdservicesapi.repository.TipoConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service

public class TipoConsultaService {

    @Autowired
    private TipoConsultaRepository repository;

    public List<TipoConsultaEntity> getTipoConsulta() {
        return repository.findAll();

    }
}
