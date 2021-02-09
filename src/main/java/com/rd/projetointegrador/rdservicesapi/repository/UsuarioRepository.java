package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import java.math.BigInteger;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, BigInteger> {
    //GRUPO1

    List<UsuarioEntity> findByNmNome(String nmNome);
    List<UsuarioEntity> findByNrCpf(String nrCpf);

    //GRUPO2
    Optional<UsuarioEntity> findByIdUsuario(BigInteger idUsuario);
}


