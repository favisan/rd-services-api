package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.AgPacienteEntity;
import com.rd.projetointegrador.rdservicesapi.entity.AgendaEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgPacienteRepository extends JpaRepository<AgPacienteEntity, BigInteger> {

    List<AgPacienteEntity> findByPaciente(UsuarioEntity paciente);

    //Grupo 4
    AgPacienteEntity findByAgenda(AgendaEntity agenda);

}