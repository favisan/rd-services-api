package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.entity.MedicacaoEntity;
import com.rd.projetointegrador.rdservicesapi.repository.MedicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicacaoService {

    @Autowired
    private MedicacaoRepository medicacaoRepository;

    public List<MedicacaoEntity> listarMedicacoes() {

        List<MedicacaoEntity> medicacaoEntity = medicacaoRepository.findAll();

        return medicacaoEntity;
    }


}
