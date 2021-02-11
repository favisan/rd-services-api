package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Periodo;
import com.rd.projetointegrador.rdservicesapi.entity.PeriodoEntity;
import com.rd.projetointegrador.rdservicesapi.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeriodoService {

    //Repository
    @Autowired private PeriodoRepository periodoRepository;

    //Listar todos os periodos
    public List<Periodo> listarPeriodos() {

        //Buscando Toda A Lista de Entity Periodo
        List<PeriodoEntity> periodosEntity = periodoRepository.findAll();

        //Convertendo a Lista Entity Periodo para Lista DTO
        List<Periodo> periodos = new ArrayList<>();
        periodos = converterPeriodosDTO(periodosEntity, periodos);

        return periodos;

    }

    //Convertendo de Entity para DTO
    public Periodo converterPeriodoDTO(PeriodoEntity periodoEntity, Periodo periodo) {

        //SETANDO OS VALORES NA DTO Periodo
        periodo.setIdPeriodo(periodoEntity.getIdPeriodo());
        periodo.setDsPerido(periodoEntity.getDsPeriodo());
        periodo.setHoraInicial(periodoEntity.getHoraInicial());

        return periodo;

    }

    //Convertendo lista Entity para Lista DTO
    public List<Periodo> converterPeriodosDTO(List<PeriodoEntity> periodosEntity, List<Periodo> periodos) {

        for(PeriodoEntity periodoEntity : periodosEntity) {
            Periodo periodo = new Periodo();
            periodo = converterPeriodoDTO(periodoEntity, periodo);

            periodos.add(periodo);
        }

        return periodos;

    }

}
