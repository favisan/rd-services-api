package br.com.rd.projetointegrador.repository;

import br.com.rd.projetointegrador.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, BigInteger> {
}
