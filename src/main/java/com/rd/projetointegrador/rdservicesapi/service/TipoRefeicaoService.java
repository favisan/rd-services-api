package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.AgServico;
import com.rd.projetointegrador.rdservicesapi.dto.Cardapio;
import com.rd.projetointegrador.rdservicesapi.dto.TipoRefeicao;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.CardapioRepository;
import com.rd.projetointegrador.rdservicesapi.repository.TipoRefeRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class TipoRefeicaoService {

    @Autowired
    private CardapioRepository cardapioRepository;
    @Autowired
    private TipoRefeRepository tipoRefeRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;


    //Tipo refeicoes por Id
    public TipoRefeEntity getTipoRefeicao(BigInteger idTipoRefeicao) {
        System.out.println("ID: " + idTipoRefeicao);
        Optional<TipoRefeEntity> optional = tipoRefeRepository.findById(idTipoRefeicao);
        TipoRefeEntity tipoRefeEntity = optional.get();

        return tipoRefeEntity;
    }

    public List<TipoRefeEntity> getTiposRefeicoes() {

        return tipoRefeRepository.findAll();
    }

    // retorna TipoRefeicoes em DTO.
    public List<TipoRefeicao> getTipoRefeicoesDTO() {

        List<TipoRefeEntity> refeEntities = tipoRefeRepository.findAll();

        List<TipoRefeicao> tipoRefeicoesDTO = new ArrayList<>();

        for (TipoRefeEntity tipoRefeEntity : refeEntities) {
            TipoRefeicao tipoRefeicaoDTO = new TipoRefeicao();

            tipoRefeicaoDTO.setIdTipoRefeicao(tipoRefeEntity.getIdTipoRefeicao());
            tipoRefeicaoDTO.setDsTipoRefeicao(tipoRefeEntity.getDsTipoRefeicao());

            tipoRefeicoesDTO.add(tipoRefeicaoDTO);
        }
        return tipoRefeicoesDTO;
    }


    // lista todas as refeicoes cadastradas do cardapio de acordo com o id paciente
    public Map<TipoRefeicao, List<Cardapio>> listarRefeicoes(BigInteger idPaciente) {

        UsuarioEntity usuarioEntity = usuarioRepository.findById(idPaciente).get();

        List<CardapioEntity> lista = cardapioRepository.findByPaciente(usuarioEntity);

        Map<TipoRefeEntity, List<CardapioEntity>> mapCardapios = new HashMap<TipoRefeEntity, List<CardapioEntity>>();

        for (CardapioEntity entity : lista) {
            TipoRefeEntity tipoRefeEntity = entity.getIdTipoRefeicao();
            List<CardapioEntity> listaCardapio;
            if (mapCardapios.containsKey(tipoRefeEntity)) {
                listaCardapio = mapCardapios.get(entity.getIdTipoRefeicao());
            } else {
                listaCardapio = new ArrayList<CardapioEntity>();
            }
            listaCardapio.add(entity);
            mapCardapios.put(entity.getIdTipoRefeicao(), listaCardapio);
        }

        Map<TipoRefeicao, List<Cardapio>> mapCardapioResul = new HashMap<TipoRefeicao, List<Cardapio>>();

        for (Map.Entry<TipoRefeEntity, List<CardapioEntity>> entrada : mapCardapios.entrySet()) {

            List<Cardapio> listaCardapio = new ArrayList<>();
            TipoRefeicao t = new TipoRefeicao();
            t.setDsTipoRefeicao(entrada.getKey().getDsTipoRefeicao());
            t.setIdTipoRefeicao(entrada.getKey().getIdTipoRefeicao());


            for (CardapioEntity cardapio : entrada.getValue()) {
                Cardapio c = new Cardapio();

                BigInteger paciente = cardapio.getPaciente().getIdUsuario();
                UsuarioEntity pacienteUsuario = usuarioRepository.findById(paciente).get();

                c.setIdPaciente(idPaciente);
                c.setIdTipoRefeicao(t);
                c.setDsDescricao(cardapio.getDsDescricao());
                c.setNomeReceita(cardapio.getNomeReceita());
                c.setQtRendimento(cardapio.getQtRendimento());
                c.setQtCalorias(cardapio.getQtCalorias());

                listaCardapio.add(c);
            }

            mapCardapioResul.put(t, listaCardapio);

        }
        return mapCardapioResul;
    }

    public List<Cardapio> getCardapioUsuario(BigInteger idPaciente) {

        UsuarioEntity usuarioEntity = usuarioRepository.findById(idPaciente).get();

        List<CardapioEntity> cardapios = cardapioRepository.findByPaciente(usuarioEntity);

        List<CardapioEntity> refeicoes = new ArrayList<>();

        //pegar todos os agendamentos do usuário
        for (CardapioEntity c : refeicoes) {
            for (CardapioEntity a : c.getIdTipoRefeicao().getCardapios()) {
               refeicoes.add(a);
            }
        }

        //transformar em agendamentos DTO
        List<Cardapio> cardDTO = conversaoAgEntityParaDTO(cardapios);

        return cardDTO;
    }


    public List<Cardapio> conversaoAgEntityParaDTO(List<CardapioEntity> cardapios) {

        List<Cardapio> cardDTO = new ArrayList<>();

            for (CardapioEntity card : cardapios) {

            Cardapio a = new Cardapio();

                TipoRefeEntity tipo = card.getIdTipoRefeicao();

         //  TipoRefeEntity tipoRefeEntity = cardapioRepository.findById(a.getIdCardapio()).get();

                a.setIdTipoRefeicao(a.getIdTipoRefeicao());
            a.setIdMedico(card.getMedico().getIdUsuario());
          a.setIdPaciente(card.getPaciente().getIdUsuario());
          a.setDsDescricao(card.getDsDescricao());
          a.setNomeReceita(card.getNomeReceita());
          a.setQtCalorias(card.getQtCalorias());
          a.setQtRendimento(card.getQtRendimento());
          a.setIdCardapio(card.getIdCardapio());

            cardDTO.add(a);
        }
        return cardDTO;
    }

}