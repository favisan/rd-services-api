package com.rd.projetointegrador.rdservicesapi.repository;


import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigInteger;


public interface UsuarioRepository extends JpaRepository<UsuarioEntity, BigInteger> {
}