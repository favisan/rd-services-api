package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.dto.Usuario;
import com.rd.projetointegrador.rdservicesapi.entity.SolicExameEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface SolicExameRepository extends JpaRepository<SolicExameEntity, BigInteger> {

    List<SolicExameEntity> findByPaciente(UsuarioEntity paciente);
}


