package com.rd.projetointegrador.rdservicesapi.service;


import com.rd.projetointegrador.rdservicesapi.entity.CardapioEntity;
import com.rd.projetointegrador.rdservicesapi.entity.TipoRefeEntity;
import com.rd.projetointegrador.rdservicesapi.dto.Cardapio;
import com.rd.projetointegrador.rdservicesapi.repository.TipoRefeRepository;

import com.rd.projetointegrador.rdservicesapi.repository.CardapioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class CardapioService {

    @Autowired
    private CardapioRepository cardapioRepository;

    @Autowired
    private TipoRefeRepository tipoRefeRepository;

   //buscar cardapio por id
    public CardapioEntity buscarCardapio(BigInteger idCardapio){
        System.out.println("ID: " + idCardapio);
        Optional<CardapioEntity> optional = cardapioRepository.findById(idCardapio);
        CardapioEntity entity = optional.get();

        return entity;
    }

    //lista todos os cardapios
    public List<CardapioEntity> buscarCardapios(BigInteger idCardapio){
        return cardapioRepository.findAll();
    }

    //cadastra um novo cardapio
    @Transactional
    public String cadastrarCardapio(Cardapio cardapio) {
        CardapioEntity entity = new CardapioEntity();

      BigInteger idTipoRefeicao = cardapio.getIdTipoRefeicao().getIdTipoRefeicao();
      TipoRefeEntity tipoRefeEntity = tipoRefeRepository.findById(idTipoRefeicao).get();

        entity.setIdMedico(cardapio.getIdMedico());
        entity.setIdPaciente(cardapio.getIdPaciente());
        entity.setIdTipoRefeicao(tipoRefeEntity);
        entity.setDsDescricao(cardapio.getDsDescricao());
        entity.setNomeReceita(cardapio.getNomeReceita());
        entity.setQtRendimento(cardapio.getQtRendimento());
        entity.setQtCalorias(cardapio.getQtCalorias());

        cardapioRepository.save(entity);

       System.out.println( cardapio.getIdCardapio()+ " , " +cardapio.getIdMedico() + " , " + cardapio.getIdPaciente());

        return "Cadastro realizado com sucesso";
    }

    //altera um cardapio já existente.
    public String alterarCardapio(Cardapio cardapio, BigInteger idCardapio) {
        CardapioEntity entity = buscarCardapio(idCardapio);

        BigInteger idTipoRefeicao = cardapio.getIdTipoRefeicao().getIdTipoRefeicao();
        TipoRefeEntity tipoRefeEntity = tipoRefeRepository.findById(idTipoRefeicao).get();

        entity.setIdMedico(cardapio.getIdMedico());
        entity.setIdPaciente(cardapio.getIdPaciente());
        entity.setIdTipoRefeicao(tipoRefeEntity);
        entity.setDsDescricao(cardapio.getDsDescricao());
        entity.setNomeReceita(cardapio.getNomeReceita());
        entity.setQtRendimento(cardapio.getQtRendimento());
        entity.setQtCalorias(cardapio.getQtCalorias());

        cardapioRepository.save(entity);

        return "Alteração" +idCardapio + " feita com sucesso!";

    }

    //excluir um cardapio
    public String excluirCardapio(BigInteger idCardapio) {
      cardapioRepository.deleteById(idCardapio);

        return "Exclusão do ID " + idCardapio + " foi realizado com sucesso";
    }


}
