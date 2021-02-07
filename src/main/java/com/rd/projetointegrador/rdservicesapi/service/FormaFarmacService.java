package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.FormaFarmac;
import com.rd.projetointegrador.rdservicesapi.entity.FormaFarmacEntity;
import com.rd.projetointegrador.rdservicesapi.repository.FormaFarmacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FormaFarmacService {

    //Repository
    @Autowired
    private FormaFarmacRepository formaFarmacRepository;

    //Listar todas as formas farmac
    public List<FormaFarmac> listarFormasFarmac() {

        //Buscando Toda A Lista de Entity FormaFarmac
        List<FormaFarmacEntity> formasFarmacEntity = formaFarmacRepository.findAll();

        //Convertendo a Lista Entity FormaFarmac para Lista DTO
        List<FormaFarmac> formasFarmac = new ArrayList<>();
        formasFarmac = converterFormasFarmacDTO(formasFarmacEntity, formasFarmac);

        return formasFarmac;

    }

    //Convertendo de Entity para DTO
    public FormaFarmac converterFormaFarmacDTO(FormaFarmacEntity formaFarmacEntity, FormaFarmac formaFarmac) {

        //SETANDO OS VALORES NA DTO FormaFarmac
        formaFarmac.setIdFormaFarmac(formaFarmacEntity.getIdFormaFarmac());
        formaFarmac.setDsFormaFarmac(formaFarmacEntity.getDsFormaFarmac());

        return formaFarmac;

    }

    //Convertendo lista Entity para Lista DTO
    public List<FormaFarmac> converterFormasFarmacDTO(List<FormaFarmacEntity> formasFarmacEntity, List<FormaFarmac> formasFarmac) {

        for(FormaFarmacEntity formaFarmacEntity : formasFarmacEntity) {
            FormaFarmac formaFarmac = new FormaFarmac();
            formaFarmac = converterFormaFarmacDTO(formaFarmacEntity, formaFarmac);

            formasFarmac.add(formaFarmac);
        }

        return formasFarmac;

    }

}
