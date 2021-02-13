package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Contrato;
import com.rd.projetointegrador.rdservicesapi.dto.Planos;
import com.rd.projetointegrador.rdservicesapi.entity.ContratoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.PlanosEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.ContratoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.PlanosRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ContratoService {
    //GRUPO1

    @Autowired
    private ContratoRepository repository;
    @Autowired
    private PlanosRepository planosRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

//    //MÉTODO: conversão de Entity para DTO
//    public Contrato conversaoContratoDTO(ContratoEntity contratoEntity, Contrato contrato) {
//
//        contrato.setIdContrato(contratoEntity.getIdContrato());
//        contrato.setDsContrato(contratoEntity.getDsContrato());
//        contrato.setDtVigencia(contratoEntity.getDtVigencia());
//        contrato.setIdPlano(contratoEntity.getPlanosEntity().getIdPlano());
//        contrato.setIdUsuario(contratoEntity.getUsuario().getIdUsuario());
//
//        return contrato;
//    }

//    //MÉTODO: conversão de DTO para Entity
//    public ContratoEntity conversaoContratoEntity(Contrato contrato, ContratoEntity contratoEntity) {
//
//        //pegar plano
//        PlanosEntity planosEntity = planosRepository.findById(contrato.getIdPlano()).get();
//
//        //pegar usuario
//        UsuarioEntity usuarioEntity = usuarioRepository.findById(contrato.getIdUsuario()).get();
//
//
//        contratoEntity.setDsContrato(contrato.getDsContrato());
//        contratoEntity.setDtVigencia(contrato.getDtVigencia());
//        contratoEntity.setPlanosEntity(planosEntity);
//        contratoEntity.setUsuario(usuarioEntity);
//
//        return contratoEntity;
//    }

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
        UsuarioEntity usuarioEntity = usuarioRepository.findById(idUsuario).get();
        List<ContratoEntity> contratosByUser = repository.findByUsuario(usuarioEntity);
        return contratosByUser;
    }

    //Grupo2
    public List<Contrato> getContratoDTOByUsuario(BigInteger idUsuario) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(idUsuario).get();
        List <ContratoEntity> contratoByUser = repository.findByUsuario(usuarioEntity);
        List <Contrato> contratoDTO = new ArrayList<>();
        for (ContratoEntity contratoEntity : contratoByUser) {
            Planos plano = new Planos();
            plano.setIdPlano(contratoEntity.getPlanosEntity().getIdPlano());
            plano.setNmPlano(contratoEntity.getPlanosEntity().getNmPlano());
            plano.setDsPlano(contratoEntity.getPlanosEntity().getDsPlano());
            Contrato contrato = new Contrato();
            contrato.setIdContrato(contratoEntity.getIdContrato());
            contrato.setDtVigencia(contratoEntity.getDtVigencia());
            contrato.setPlano(plano);

            contratoDTO.add(contrato);
        }
        return contratoDTO;
    }

//    @Transactional
//    public String cadastrarContrato(Contrato contrato) {
//
//        ContratoEntity contratoEntity = new ContratoEntity();
//        contratoEntity = conversaoContratoEntity(contrato, contratoEntity);
//
//        repository.save(contratoEntity);
//
//        return "Contrato cadastrado com sucesso";
//
//    }

//    @Transactional
//    public String alterarContrato(Contrato contrato, BigInteger idContrato) {
//
//        ContratoEntity contratoEntity = getContrato(idContrato);
//        contratoEntity = conversaoContratoEntity(contrato, contratoEntity);
//
//        repository.save(contratoEntity);
//        return "Alteração realizada com sucesso";
//    }

    //Confirmar se haverá ou não exclusão do contrato
    public String excluirContrato(BigInteger idContrato) {
        repository.deleteById(idContrato);
        return "Exclusão de contrato realizada com sucesso";
    }



}
