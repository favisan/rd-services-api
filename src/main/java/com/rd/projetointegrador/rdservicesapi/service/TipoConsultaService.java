package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.TipoConsulta;
import com.rd.projetointegrador.rdservicesapi.entity.TipoConsultaEntity;
import com.rd.projetointegrador.rdservicesapi.repository.TipoConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class TipoConsultaService {

    @Autowired private TipoConsultaRepository repository;

    //Grupo2 - Get tipo de consulta dto by id
    public TipoConsulta getTipoConsultabyId(BigInteger idTipoConsulta){
        TipoConsultaEntity tipoConsultaEntity = repository.findById(idTipoConsulta).get();
        TipoConsulta tipoConsultaDTO = new TipoConsulta();
        tipoConsultaDTO.setIdTipoConsulta(tipoConsultaEntity.getIdTipoConsulta());
        tipoConsultaDTO.setDsTipoConsulta(tipoConsultaEntity.getDsTipoConsulta());

        return tipoConsultaDTO;
    }
}
