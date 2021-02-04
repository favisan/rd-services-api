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
    @Autowired PlanosRepository planosRepository;

    //MÉTODO: conversão de Entity para DTO
    public Contrato conversaoContratoDTO(ContratoEntity contratoEntity, Contrato contrato){

        contrato.setIdContrato(contratoEntity.getIdContrato());
        contrato.setDsContrato(contratoEntity.getDsContrato());
        contrato.setDtVigencia(contratoEntity.getDtVigencia());
        contrato.setIdPlano(contratoEntity.getPlanosEntity().getIdPlano());
        contrato.setIdUsuario(contratoEntity.getIdUsuario());

        return contrato;
    }

    //MÉTODO: conversão de DTO para Entity
    public ContratoEntity conversaoContratoEntity(Contrato contrato, ContratoEntity contratoEntity) {

        //pegar plano
        PlanosEntity planosEntity = planosRepository.findById(contrato.getIdPlano()).get();

        contratoEntity.setDsContrato(contrato.getDsContrato());
        contratoEntity.setDtVigencia(contrato.getDtVigencia());
        contratoEntity.setPlanosEntity(planosEntity);
        contratoEntity.setIdUsuario(contrato.getIdUsuario());

        return contratoEntity;
    }

    //MÉTODOS RETORNANDO A ENTITY
    public ContratoEntity getContrato(BigInteger idContrato) {
        System.out.println("IdContrato: " + idContrato);
        Optional<ContratoEntity> optional = repository.findById(idContrato);
        return optional.get();

    }
    public List<ContratoEntity> getContratos(BigInteger idContrato) {
        return repository.findAll();

    }
    public List<ContratoEntity> getContratosByUsuario(BigInteger idUsuario) {
        List<ContratoEntity> contratosByUser = repository.findByIdUsuario(idUsuario);
        return contratosByUser;
    }

    @Transactional
    public String cadastrarContrato(Contrato contrato){

        ContratoEntity contratoEntity = new ContratoEntity();
        contratoEntity = conversaoContratoEntity(contrato, contratoEntity);

        repository.save(contratoEntity);

        return "Contrato cadastrado com sucesso";

    }

    @Transactional
    public String alterarContrato(Contrato contrato, BigInteger idContrato){

        ContratoEntity contratoEntity = getContrato(idContrato);
        contratoEntity = conversaoContratoEntity(contrato, contratoEntity);

        repository.save(contratoEntity);
        return "Alteração realizada com sucesso";
    }

    //Confirmar se haverá ou não exclusão do contrato
    public String excluirContrato(BigInteger idContrato){
        repository.deleteById(idContrato);
        return "Exclusão de contrato realizada com sucesso";

    }

}
