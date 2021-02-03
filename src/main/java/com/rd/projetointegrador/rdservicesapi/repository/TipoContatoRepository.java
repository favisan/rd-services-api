package com.rd.projetointegrador.rdservicesapi.repository;


import com.rd.projetointegrador.rdservicesapi.entity.TipoContatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigInteger;

public interface TipoContatoRepository extends JpaRepository<TipoContatoEntity, BigInteger> {
}