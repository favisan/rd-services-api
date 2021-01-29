package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Contrato;
import com.rd.projetointegrador.rdservicesapi.dto.Planos;
import com.rd.projetointegrador.rdservicesapi.entity.ContratoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.PlanosEntity;
import com.rd.projetointegrador.rdservicesapi.repository.ContratoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.PlanosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class ContratoService {
    @Autowired
    private ContratoRepository repository;

    public ContratoEntity getContrato(BigInteger idContrato) {
        System.out.println("IdContrato: " + idContrato);
        Optional<ContratoEntity> optional = repository.findById(idContrato);
        return optional.get();

    }

    public List<ContratoEntity> getContratos(BigInteger idContrato) {
        return repository.findAll();

    }

    @Transactional
    public String cadastrarContrato(Contrato contrato){

        ContratoEntity contratoEntity = new ContratoEntity();

        contratoEntity.setDsContrato(contrato.getDsContrato());
        contratoEntity.setDtVigencia(contrato.getDtVigencia());
        contratoEntity.setIdPlano(contrato.getIdPlano());
        contratoEntity.setIdUsuario(contrato.getIdUsuario());

        repository.save(contratoEntity);

        System.out.println(contrato.getIdContrato() + " . " + contrato.getDsContrato() + " . " +contrato.getDtVigencia() + " . " + contrato.getIdPlano()+ " . " + contrato.getIdUsuario());

        return "Contrato cadastrado com sucesso";

    }

    @Transactional
    public String alterarContrato(Contrato contrato, BigInteger idContrato){

        ContratoEntity contratoEntity = getContrato(idContrato);

        contratoEntity.setDsContrato(contrato.getDsContrato());
        contratoEntity.setDtVigencia(contrato.getDtVigencia());
        contratoEntity.setIdPlano(contrato.getIdPlano());
        contratoEntity.setIdUsuario(contrato.getIdUsuario());

        contratoEntity = repository.save(contratoEntity);
        return "Alteração realizada com sucesso";
    }

    //Confirmar se haverá ou não exclusão do contrato
    public String excluirContrato(BigInteger idContrato){
        repository.deleteById(idContrato);
        return "Exclusão de contrato realizada com sucesso";

    }

}
