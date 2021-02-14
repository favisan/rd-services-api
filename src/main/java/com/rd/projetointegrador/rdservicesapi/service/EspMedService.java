package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.EspMed;
import com.rd.projetointegrador.rdservicesapi.entity.AgendaEntity;
import com.rd.projetointegrador.rdservicesapi.entity.EspMedEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EspMedService {

    //Grupo2 - Listar as especialidades médicas das agendas médicas disponíveis
    @Autowired private AgendaRepository agendaRepository;
    public Set<EspMed> getEspByAgenda() {
        List<AgendaEntity> agendas = agendaRepository.findAll();
        List<AgendaEntity> agendasDisponiveis = new ArrayList<>();
        for (AgendaEntity agendaEntity : agendas){
            if (agendaEntity.getDisponibilidade() == 1){
                agendasDisponiveis.add(agendaEntity);
            }
        }
        List<UsuarioEntity> usuarios = new ArrayList<>();
        for (AgendaEntity agendaEntity : agendasDisponiveis) {
            UsuarioEntity usuario = agendaEntity.getMedico();
            usuarios.add(usuario);
        }
        Set<EspMedEntity> especialidadesEntity = new HashSet<>();
        for (UsuarioEntity usuarioEntity : usuarios) {
            EspMedEntity especialidade = usuarioEntity.getEspMed();
            if (especialidade != null) {
                especialidadesEntity.add(especialidade);
            }
        }
        Set<EspMed> especialidadesDto = new HashSet<>();
        for (EspMedEntity espMedEntity : especialidadesEntity){
            EspMed espMedDto = new EspMed();
            espMedDto.setDsEspMed(espMedEntity.getDsEspMed());
            espMedDto.setIdEspMed(espMedEntity.getIdEspMed());
            especialidadesDto.add(espMedDto);
        }
        return especialidadesDto;
    }
}
