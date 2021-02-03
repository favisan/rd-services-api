package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.entity.EspMedEntity;
import com.rd.projetointegrador.rdservicesapi.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository espMedRepository;

    public List<EspMedEntity> getEspecialidades(){

        return espMedRepository.findAll();
    }
}
