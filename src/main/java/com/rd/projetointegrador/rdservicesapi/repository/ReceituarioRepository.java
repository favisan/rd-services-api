package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.ProntuarioEntity;
import com.rd.projetointegrador.rdservicesapi.entity.ReceituarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ReceituarioRepository extends JpaRepository<ReceituarioEntity, BigInteger> {

    List<ReceituarioEntity> findByProntuario(ProntuarioEntity prontuario);

}
