package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Endereco;
import com.rd.projetointegrador.rdservicesapi.dto.Usuario;
import com.rd.projetointegrador.rdservicesapi.entity.EnderecoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired private EnderecoRepository repository;

//    //MÉTODO: conversão de DTO para Entity
//    public EnderecoEntity conversaoEnderecoEntity(Endereco endereco, EnderecoEntity enderecoEntity) {
//
//        enderecoEntity.setIdEndereco(endereco.getIdEndereco());
//        enderecoEntity.setDsEndereco(endereco.getDsEndereco());
//        enderecoEntity.setDsBairro(endereco.getDsBairro());
//        enderecoEntity.setDsComplemento(endereco.getDsComplemento());
//        enderecoEntity.setIdCidade(endereco.getIdCidade());
//        enderecoEntity.setNrCep(endereco.getNrCep());
//
//        return enderecoEntity;
//    }

//    //MÉTODO: conversão de Entity para DTO
//    public Endereco conversaoEnderecoDTO(EnderecoEntity enderecoEntity, Endereco endereco) {
//
//        endereco.setIdEndereco(enderecoEntity.getIdEndereco());
//        endereco.setDsEndereco(enderecoEntity.getDsEndereco());
//        endereco.setDsBairro(enderecoEntity.getDsBairro());
//        endereco.setDsComplemento(enderecoEntity.getDsComplemento());
//        endereco.setIdCidade(enderecoEntity.getIdCidade());
//        endereco.setNrCep(enderecoEntity.getNrCep());
//
//        return endereco;
//    }

//    //MÉTODO: conversão ListaDTO para ListaEntity
//    public List<EnderecoEntity> conversaoEnderecosEntities(List<Endereco> enderecos, List<EnderecoEntity> enderecosEntities){
//
//        for(Endereco endereco: enderecos) {
//            EnderecoEntity enderecoEntity = new EnderecoEntity();
//            enderecoEntity = conversaoEnderecoEntity(endereco, enderecoEntity);
//
//            enderecosEntities.add(enderecoEntity);
//        }
//
//        return enderecosEntities;
//    }

//    //MÉTODO: conversão listaEntity para ListaDTO
//    public List<Endereco> conversaoEnderecosDTO(List<EnderecoEntity> enderecosEntities, List<Endereco> enderecos){
//
//        for(EnderecoEntity enderecoEntity: enderecosEntities) {
//            Endereco endereco = new Endereco();
//            endereco = conversaoEnderecoDTO(enderecoEntity, endereco);
//
//            enderecos.add(endereco);
//        }
//        return enderecos;
//    }
//}
//    //MÉTODO: conversão listaEntity para ListaDTO
//    public List<Endereco> conversaoEnderecosDTO(List<EnderecoEntity> enderecosEntities, List<Endereco> enderecos){
//
//        for(EnderecoEntity enderecoEntity: enderecosEntities) {
//            Endereco endereco = new Endereco();
//            endereco = conversaoEnderecoDTO(enderecoEntity, endereco);
//
//            enderecos.add(endereco);
//        }
//        return enderecos;
//    }
}
