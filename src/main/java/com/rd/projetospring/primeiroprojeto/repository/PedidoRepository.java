package com.rd.projetospring.primeiroprojeto.repository;

import com.rd.projetospring.primeiroprojeto.entity.EnderecoEntity;
import com.rd.projetospring.primeiroprojeto.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntity, BigInteger> {

    List<PedidoEntity> findByIdPaciente(BigInteger idPaciente);
}
