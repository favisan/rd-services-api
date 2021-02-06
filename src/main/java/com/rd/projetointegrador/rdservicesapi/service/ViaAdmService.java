package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.entity.ViaAdmEntity;
import com.rd.projetointegrador.rdservicesapi.repository.ViaAdmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViaAdmService {

    @Autowired
    private ViaAdmRepository viaAdmRepository;

    public List<ViaAdmEntity> listarViasAdm() {

        List<ViaAdmEntity> viasAdmEntity = viaAdmRepository.findAll();

        return viasAdmEntity;
    }

}
