package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.EspMed;
import com.rd.projetointegrador.rdservicesapi.entity.EspMedEntity;
import org.springframework.stereotype.Service;


@Service
public class EspMedService {

    //MÉTODO: conversão de Entity para DTO
    public EspMed conversaoEspMedDTO(EspMedEntity espMedEntity, EspMed espMed) {

        espMed.setIdEspMed(espMedEntity.getIdEspMed());
        espMed.setDsEspMed(espMedEntity.getDsEspMed());

        return espMed;
    }

}
