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

    @Autowired
    private EnderecoService enderecoService;


    public List<Loja> getLojas(){

        List<LojaEntity> lojasEntities = repository.findAll();
        List<Loja> lojasDTO = conversaoLojasDTO(lojasEntities);
        return lojasDTO;
    }

    public List<Loja> getLojasPorLocalidade(String local){

        List<LojaEntity> lojasEntities = repository.getLojasPorLocalidade(local, local, local);
        List<Loja> lojasDTO = conversaoLojasDTO(lojasEntities);
        return lojasDTO;
    }


    //MÉTODO: conversão listaEntity para ListaDTO
    public List<Loja> conversaoLojasDTO(List<LojaEntity> lojasEntities){

        List<Loja> lojasDTO = new ArrayList<>();

        for(LojaEntity lojaEntity: lojasEntities) {
            Loja loja = conversaoLojaDTO(lojaEntity);
            lojasDTO.add(loja);
        }
        return lojasDTO;
    }

    //MÉTODO: conversão de Entity para DTO
    public Loja conversaoLojaDTO(LojaEntity lojaEntity) {

        Loja loja = new Loja();
        loja.setIdLoja(lojaEntity.getIdLoja());
        loja.setNmLoja(lojaEntity.getNmLoja());

        //pegar todos os endereços dessa loja
        List<EnderecoEntity> enderecosEntity = lojaEntity.getEnderecos();
        List<Endereco> enderecosDTO = new ArrayList<>();

        enderecosDTO = enderecoService.conversaoEnderecosDTO(enderecosEntity, enderecosDTO);
        loja.setEnderecos(enderecosDTO);

        //pegar todos os contatos dessa loja
        List<ContatoEntity> contatos = repositoryContato.findByLoja(lojaEntity);
        List<Contato> contatosDTO = new ArrayList<>();

        for(ContatoEntity contatoEntity : contatos){
            Contato contato = new Contato();
            contato.setNrDdd(contatoEntity.getNrDdd());
            contato.setDsContato(contatoEntity.getDsContato());
            contatosDTO.add(contato);
        }
        loja.setContatos(contatosDTO);

        return loja;
    }

}
