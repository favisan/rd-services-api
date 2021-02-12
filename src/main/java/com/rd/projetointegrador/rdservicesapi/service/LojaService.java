package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Contato;
import com.rd.projetointegrador.rdservicesapi.dto.Endereco;
import com.rd.projetointegrador.rdservicesapi.dto.Loja;
import com.rd.projetointegrador.rdservicesapi.entity.ContatoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.EnderecoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.LojaEntity;
import com.rd.projetointegrador.rdservicesapi.repository.ContatoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LojaService {

    @Autowired
    private LojaRepository repository;

    @Autowired
    private ContatoRepository repositoryContato;


    public LojaEntity getLoja(BigInteger id){
        System.out.println("ID: " + id);
        Optional<LojaEntity> optional = repository.findById(id);
        LojaEntity entity = optional.get();

        return entity;
    }

    public List<LojaEntity> getLojas2(){

        return repository.findAll();
    }

    public List<Loja> getLojas(){

        List<LojaEntity> lojasEntities = repository.findAll();

        List<Loja> lojasDTO = new ArrayList<>();

        for(LojaEntity lojaEntity : lojasEntities){
            Loja lojaDTO = new Loja();
            lojaDTO.setIdLoja(lojaEntity.getIdLoja());
            lojaDTO.setNmLoja(lojaEntity.getNmLoja());

            //pegar todos os endereços dessa loja
            List<EnderecoEntity> enderecosEntity = lojaEntity.getEnderecos();

            List<Endereco> enderecosDTO = new ArrayList<>();

            for(EnderecoEntity enderecoEntity : enderecosEntity){
                Endereco endereco = new Endereco();
                endereco.setIdEndereco(enderecoEntity.getIdEndereco());
                endereco.setDsEndereco(enderecoEntity.getDsEndereco());
                endereco.setDsBairro(enderecoEntity.getDsBairro());
                endereco.setNrCep(enderecoEntity.getNrCep());

                enderecosDTO.add(endereco);
            }

            //pegar todos os contatos dessa loja
            List<ContatoEntity> contatos = repositoryContato.findByLoja(lojaEntity);
            System.out.println("Contatos para o ID loja: " + lojaDTO.getIdLoja());
            List<Contato> contatosDTO = new ArrayList<>();

            for(ContatoEntity contatoEntity : contatos){
                Contato contato = new Contato();
                contato.setNrDdd(contatoEntity.getNrDdd());
                contato.setDsContato(contatoEntity.getDsContato());
                contatosDTO.add(contato);
            }

            lojaDTO.setEnderecos(enderecosDTO);
            lojaDTO.setContatos(contatosDTO);
            lojasDTO.add(lojaDTO);
        }
        return lojasDTO;
    }
}
