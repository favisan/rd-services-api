package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntity, BigInteger> {

    List<PedidoEntity> findByIdPaciente(BigInteger idPaciente);
}
