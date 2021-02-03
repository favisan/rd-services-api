package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.ProntuarioEntity;
import com.rd.projetointegrador.rdservicesapi.entity.SolicExameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import java.util.List;

@Repository
public interface SolicExameRepository extends JpaRepository<SolicExameEntity, BigInteger> {

    List<SolicExameEntity> findByProntuario(ProntuarioEntity prontuario);
}


