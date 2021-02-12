package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigInteger;

public interface StatusRepository extends JpaRepository<StatusEntity, BigInteger> {

}
