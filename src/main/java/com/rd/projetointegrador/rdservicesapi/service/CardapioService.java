package com.rd.projetointegrador.rdservicesapi.service;


import com.rd.projetointegrador.rdservicesapi.dto.OutputProgramaNutricional;
import com.rd.projetointegrador.rdservicesapi.entity.AtendimentoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.CardapioEntity;
import com.rd.projetointegrador.rdservicesapi.entity.TipoRefeEntity;
import com.rd.projetointegrador.rdservicesapi.dto.Cardapio;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.AtendimentoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.TipoRefeRepository;

import com.rd.projetointegrador.rdservicesapi.repository.CardapioRepository;

import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
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
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AtendimentoRepository atendimentoRepository;


    //Exibir dados do paciente
    public OutputProgramaNutricional exibirDadosPaciente(BigInteger paciente) {
        UsuarioEntity IdPaciente = usuarioRepository.findById(paciente).get();

        List<AtendimentoEntity> listaPaciente = atendimentoRepository.findByPacienteOrderByDtAtendimento(IdPaciente);

        //Try para caso o paciente exista no banco, mas não tem um atendimento
        try {
            AtendimentoEntity ultimoAtend = listaPaciente.get(0);

            OutputProgramaNutricional op = new OutputProgramaNutricional();

            op.setNome(IdPaciente.getNmNome());
            op.setDtNascimento(IdPaciente.getDtNascimento());
            op.setVlAltura(ultimoAtend.getVlAltura());
            op.setVlPeso(ultimoAtend.getVlPeso());
            op.setDsHabitosVicios(ultimoAtend.getDsHabitosVicios());
            op.setDsAlergiasRestricoes(ultimoAtend.getDsAlergiasRestricoes());
            op.setDsObjetivo(ultimoAtend.getProntuario().getDsObjetivo());

            return op;
        } catch (Exception e) {
            OutputProgramaNutricional op = new OutputProgramaNutricional();

            op.setNome(IdPaciente.getNmNome());
            // COMO COLOCA?   op.setDtNascimento();
            op.setVlAltura(0.0F);
            op.setVlPeso(0.0F);
            op.setDsHabitosVicios("");
            op.setDsAlergiasRestricoes("");
            op.setDsObjetivo("");

            return op;
        }
    }

    //buscar cardapio por id
    public CardapioEntity buscarCardapio(BigInteger idCardapio) {
        System.out.println("ID: " + idCardapio);
        Optional<CardapioEntity> optional = cardapioRepository.findById(idCardapio);
        CardapioEntity entity = optional.get();

        return entity;
    }

    //buscar cardapio por id do paciente
    public List<CardapioEntity> buscarCardapioPorPaciente(UsuarioEntity idPaciente) {
//        System.out.println("ID: " + idPaciente);
//        Optional<CardapioEntity> optional = cardapioRepository.findById(idPaciente);
//        CardapioEntity entity = optional.get();
//
//        return entity;
        return cardapioRepository.findByPaciente(idPaciente);
    }

    //lista todos os cardapios
    public List<CardapioEntity> buscarCardapios(BigInteger idCardapio) {
        return cardapioRepository.findAll();
    }


    // cadastra um novo cardapio
    @Transactional
    public String cadastrarCardapio(Cardapio cardapio) {
        CardapioEntity entity = new CardapioEntity();

        BigInteger idTipoRefeicao = cardapio.getIdTipoRefeicao().getIdTipoRefeicao();
        TipoRefeEntity tipoRefeEntity = tipoRefeRepository.findById(idTipoRefeicao).get();

        BigInteger medico = cardapio.getIdMedico();
        UsuarioEntity usuarioMedico = usuarioRepository.findById(medico).get();

        BigInteger paciente = cardapio.getIdPaciente();
        UsuarioEntity usuarioPaciente = usuarioRepository.findById(medico).get();

        entity.setMedico(usuarioMedico);
        entity.setPaciente(usuarioPaciente);
        entity.setIdTipoRefeicao(tipoRefeEntity);
        entity.setDsDescricao(cardapio.getDsDescricao());
        entity.setNomeReceita(cardapio.getNomeReceita());
        entity.setQtRendimento(cardapio.getQtRendimento());
        entity.setQtCalorias(cardapio.getQtCalorias());

        cardapioRepository.save(entity);

        System.out.println(cardapio.getIdCardapio() + " , " + cardapio.getIdMedico() + " , " + cardapio.getIdPaciente());

        return "Cadastro realizado com sucesso";
    }

    //altera um cardapio já existente.
    public String alterarCardapio(Cardapio cardapio, BigInteger idCardapio) {
        CardapioEntity entity = buscarCardapio(idCardapio);

        BigInteger idTipoRefeicao = cardapio.getIdTipoRefeicao().getIdTipoRefeicao();
        TipoRefeEntity tipoRefeEntity = tipoRefeRepository.findById(idTipoRefeicao).get();

        BigInteger medico = cardapio.getIdMedico();
        UsuarioEntity usuarioMedico = usuarioRepository.findById(medico).get();

        BigInteger paciente = cardapio.getIdPaciente();
        UsuarioEntity usuarioPaciente = usuarioRepository.findById(paciente).get();

        entity.setIdTipoRefeicao(tipoRefeEntity);
        entity.setMedico(usuarioMedico);
        entity.setPaciente(usuarioPaciente);
        entity.setDsDescricao(cardapio.getDsDescricao());
        entity.setNomeReceita(cardapio.getNomeReceita());
        entity.setQtRendimento(cardapio.getQtRendimento());
        entity.setQtCalorias(cardapio.getQtCalorias());

        cardapioRepository.save(entity);

        return "Alteração do " + idCardapio + " feita com sucesso!";
    }


    //excluir um cardapio por Id
    public String excluirCardapio(BigInteger idCardapio) {
        cardapioRepository.deleteById(idCardapio);

        return "Exclusão do ID do " + idCardapio + " foi realizada com sucesso";
    }


}
