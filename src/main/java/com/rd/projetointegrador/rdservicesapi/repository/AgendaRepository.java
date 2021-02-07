package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.dto.Agenda;
import com.rd.projetointegrador.rdservicesapi.entity.AgendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<AgendaEntity, BigInteger> {
    List<Agenda> findByDiaDisponivel(Date diaDisponivel);
}
