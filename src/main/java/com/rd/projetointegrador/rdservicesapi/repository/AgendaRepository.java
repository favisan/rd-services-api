package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.dto.InputMedico;
import com.rd.projetointegrador.rdservicesapi.entity.AgendaEntity;
import com.rd.projetointegrador.rdservicesapi.entity.TipoConsultaEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<AgendaEntity, BigInteger> {

    //Grupo 2
    List<AgendaEntity> findByTipoConsulta(TipoConsultaEntity tipoConsulta);

    //Grupo 4
    List<AgendaEntity> findByData(Date data);

    List<AgendaEntity> findByMedico(UsuarioEntity medico);

    List<AgendaEntity> findByDataAndDisponibilidade(Date data, Integer disponibilidade);

}
