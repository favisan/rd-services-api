package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.entity.TipoExameEntity;
import com.rd.projetointegrador.rdservicesapi.repository.TipoExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TipoExameService {

    @Autowired
    private TipoExameRepository repository;

    public List<TipoExameEntity> listarTiposDeExames() {
        List<TipoExameEntity> cidadeEntity = repository.findAll();

        return cidadeEntity;
    }
}
