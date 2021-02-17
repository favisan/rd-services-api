package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.*;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.ContatoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    @Autowired
    private LojaRepository lojaRepository;

    public List<ContatoEntity> getContatosLoja(BigInteger id) {
        System.out.println("Contatos para o ID loja: " + id);
        LojaEntity lojaEntity = lojaRepository.findById(id).get();
        List<ContatoEntity> contatos = repository.findByLoja(lojaEntity);

        return contatos;
    }

    //MÉTODO: conversão de Entity para DTO
    public Contato conversaoContatoDTO(ContatoEntity contatoEntity, Contato contato) {

        contato.setIdContato(contatoEntity.getIdContato());
        contato.setIdUsuario(contatoEntity.getIdUsuario());
        //contato.setIdLoja(contatoEntity.getLoja().getIdLoja());

        TipoContatoEntity tipoContatoEntity = contatoEntity.getTipoContato();
        TipoContato tipoContato = new TipoContato();
        tipoContato.setDsTipoContato(tipoContatoEntity.getDsTipoContato());
        tipoContato.setIdTipoContato(tipoContatoEntity.getIdTipoContato());

        contato.setTipoContato(tipoContato);
        contato.setNrDdi(contatoEntity.getNrDdi());
        contato.setNrDdd(contatoEntity.getNrDdd());
        contato.setNrRamal(contatoEntity.getNrRamal());
        contato.setDsContato(contato.getDsContato());

        return contato;
    }

    //MÉTODO: conversão listaEntity para ListaDTO
    public List<Contato> conversaoContatosDTO(List<ContatoEntity> contatosEntities, List<Contato> contatos) {

        for(ContatoEntity contatoEntity: contatosEntities) {
            Contato contato = new Contato();
            contato = conversaoContatoDTO(contatoEntity, contato);

            contatos.add(contato);
        }

        return contatos;
    }

}
