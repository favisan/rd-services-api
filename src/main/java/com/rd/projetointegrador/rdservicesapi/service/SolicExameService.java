package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.SolicExame;
import com.rd.projetointegrador.rdservicesapi.entity.SolicExameEntity;
import com.rd.projetointegrador.rdservicesapi.repository.SolicExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SolicExameService {

    @Autowired
    private SolicExameRepository repository;

    public String cadastrarSolicExame(SolicExame solicExame) {
        SolicExameEntity entity = new SolicExameEntity();
        entity.setIdProntuario(solicExame.getIdProntuario());
        entity.setIdPaciente(solicExame.getIdPaciente());
        entity.setIdMedico(solicExame.getIdMedico());
        entity.setDtSolicitacao(solicExame.getDtSolicitacao());
        entity.setDsIndicacaoClin(solicExame.getDsIndicacaoClin());

        repository.save(entity);

        return "Cadastro realizado com sucesso!";
    }
}
