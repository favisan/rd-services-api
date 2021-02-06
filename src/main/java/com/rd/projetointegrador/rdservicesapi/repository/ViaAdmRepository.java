package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.ViaAdmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ViaAdmRepository extends JpaRepository<ViaAdmEntity, BigInteger> {

}
