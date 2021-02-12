package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.AgPacienteEntity;
import com.rd.projetointegrador.rdservicesapi.entity.CardapioEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface CardapioRepository extends JpaRepository<CardapioEntity, BigInteger> {

    //List<CardapioEntity> findByPaciente(UsuarioEntity paciente);

    List<CardapioEntity> findByPaciente(UsuarioEntity idPaciente);
}
