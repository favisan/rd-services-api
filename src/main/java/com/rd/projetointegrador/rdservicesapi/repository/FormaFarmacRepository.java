package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.FormaFarmacEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface FormaFarmacRepository extends JpaRepository<FormaFarmacEntity, BigInteger> {

}
