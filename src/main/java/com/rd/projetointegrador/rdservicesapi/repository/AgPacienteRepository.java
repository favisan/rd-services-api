package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.AgPacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface AgPacienteRepository extends JpaRepository<AgPacienteEntity, BigInteger> {
    Optional<AgPacienteEntity> findByIdAgPaciente(BigInteger idAgPaciente);
    Optional<AgPacienteEntity> findByPaciente(UsuarioEntity paciente);

}