package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, BigInteger> {

    List<UsuarioEntity> findByNmNome(String nmNome);

    List<UsuarioEntity> findByNrCpf(String nrCpf);

    Optional<UsuarioEntity> findByIdUsuario(BigInteger idUsuario);
}