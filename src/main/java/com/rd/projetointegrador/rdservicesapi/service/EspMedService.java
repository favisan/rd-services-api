package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.EspMed;
import com.rd.projetointegrador.rdservicesapi.entity.AgendaEntity;
import com.rd.projetointegrador.rdservicesapi.entity.EspMedEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.AgendaRepository;
import com.rd.projetointegrador.rdservicesapi.repository.EspMedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.rd.projetointegrador.rdservicesapi.entity.EspMedEntity;
import org.springframework.stereotype.Service;


@Service
public class EspMedService {

    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private EspMedRepository repository;

    //Grupo2 - Listar as especialidades médicas das agendas médicas disponíveis
    public Set<EspMed> getEspByAgenda(BigInteger idTipoConsulta) {
        //Busca as agendas médicas disponiveis
        List<AgendaEntity> agendasDisponiveis = agendaRepository.findByDisponibilidade(1);
        List<AgendaEntity> agendasFiltradas = new ArrayList<>();

        //Filtra por tipo de consulta
        for (AgendaEntity agendaEntity : agendasDisponiveis){
            BigInteger tipoConsultaAgenda = agendaEntity.getTipoConsulta().getIdTipoConsulta();
            if (idTipoConsulta.equals(BigInteger.valueOf(2))) {
                if (tipoConsultaAgenda.equals(BigInteger.valueOf(2))) {
                    agendasFiltradas.add(agendaEntity);
                }
            } else {
                agendasFiltradas.add(agendaEntity);
            }
        }
        //Encontra os médicos dessas agendas disponives
        List<UsuarioEntity> usuarios = new ArrayList<>();
        for (AgendaEntity agendaEntity : agendasFiltradas) {
            UsuarioEntity usuario = agendaEntity.getMedico();
            usuarios.add(usuario);
        }
        //Encontra as especialidades dessas agendas disponíveis
        Set<EspMedEntity> especialidadesEntity = new HashSet<>();
        for (UsuarioEntity usuarioEntity : usuarios) {
            EspMedEntity especialidade = usuarioEntity.getEspMed();
            if (especialidade != null) {
                especialidadesEntity.add(especialidade);
            }
        }
        Set<EspMed> especialidadesDto = new HashSet<>();
        for (EspMedEntity espMedEntity : especialidadesEntity) {
            EspMed espMedDto = new EspMed();
            espMedDto.setDsEspMed(espMedEntity.getDsEspMed());
            espMedDto.setIdEspMed(espMedEntity.getIdEspMed());
            especialidadesDto.add(espMedDto);
        }
        return especialidadesDto;
    }

    //Grupo2 - Get tipo de consulta dto by id
    public EspMed getEspMedbyId(BigInteger idEspMed){
        EspMedEntity espMedEntity = repository.findById(idEspMed).get();
        EspMed espMedDTO = new EspMed();
        espMedDTO.setIdEspMed(espMedEntity.getIdEspMed());
        espMedDTO.setDsEspMed(espMedEntity.getDsEspMed());

        return espMedDTO;
    }

    //MÉTODO: conversão de Entity para DTO
    public EspMed conversaoEspMedDTO(EspMedEntity espMedEntity, EspMed espMed) {

        espMed.setIdEspMed(espMedEntity.getIdEspMed());
        espMed.setDsEspMed(espMedEntity.getDsEspMed());

        return espMed;
    }

}