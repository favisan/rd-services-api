package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.entity.EspMedEntity;
import com.rd.projetointegrador.rdservicesapi.repository.EspMedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspMedService {

    @Autowired
    private EspMedRepository repository;

    public List<EspMedEntity> getEspecialidades(){
        return repository.findAll();
    }
}
