package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.entity.FormaFarmacEntity;
import com.rd.projetointegrador.rdservicesapi.repository.FormaFarmacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaFarmacService {

    @Autowired
    private FormaFarmacRepository formaFarmacRepository;

    public List<FormaFarmacEntity> listarFormasFarmac() {

        List<FormaFarmacEntity> formasFarmacEntity = formaFarmacRepository.findAll();

        return formasFarmacEntity;
    }

}
