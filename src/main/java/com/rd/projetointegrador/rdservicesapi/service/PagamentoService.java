package com.rd.projetointegrador.rdservicesapi.service;


import com.rd.projetointegrador.rdservicesapi.dto.*;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {
    //GRUPO1

    @Autowired private PagamentoRepository repository;
    @Autowired private CartaoRepository cartaoRepository;
    @Autowired private ContratoRepository contratoRepository;
    @Autowired private AgPacienteRepository agPacienteRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    //MÉTODO: conversão de DTO para Entity
    public PagamentoEntity conversaoPagamentoEntity(Pagamento pagamento, PagamentoEntity pagamentoEntity) {

        pagamentoEntity.setIdContrato(pagamento.getIdContrato());
        pagamentoEntity.setIdFormaPgt(pagamento.getIdFormaPgt());
        pagamentoEntity.setIdNF(pagamento.getIdNF());
//        pagamentoEntity.setIdAgPaciente(pagamento.getIdAgPaciente());
        pagamentoEntity.setIdPedido(pagamento.getIdPedido());
        pagamentoEntity.setVlPagamento(pagamento.getVlPagamento());
        pagamentoEntity.setDtPagamento(pagamento.getDtPagamento());
        pagamentoEntity.setNrParcela(pagamento.getNrParcela());
        pagamentoEntity.setIdCartao(pagamento.getIdCartao());

        // pagamentoEntity.setIdCartao(cartaoEntity);

        return pagamentoEntity;
    }
    //MÉTODO: conversão de Entity para DTO
    public Pagamento conversaoPagamentoDTO(PagamentoEntity pagamentoEntity, Pagamento pagamento) {
        pagamento.setIdContrato(pagamentoEntity.getIdContrato());
        pagamento.setIdFormaPgt(pagamentoEntity.getIdFormaPgt());
        pagamento.setIdNF(pagamentoEntity.getIdNF());
//        pagamento.setIdAgPaciente(pagamentoEntity.getIdAgPaciente());
        pagamento.setIdPedido(pagamentoEntity.getIdPedido());
        pagamento.setVlPagamento(pagamentoEntity.getVlPagamento());
        pagamento.setDtPagamento(pagamentoEntity.getDtPagamento());
        pagamento.setNrParcela(pagamentoEntity.getNrParcela());
        pagamento.setIdCartao(pagamentoEntity.getIdCartao());

        return pagamento;
    }
    //MÉTODO: conversão de ListaDTO para ListaEntity()
    public List<PagamentoEntity> conversaoPagamentosEntity(List<Pagamento> pagamentos,List<PagamentoEntity> pagamentosEntities) {

        for(Pagamento pagamento: pagamentos) {
            PagamentoEntity pagamentoEntity = new PagamentoEntity();
            pagamentoEntity = conversaoPagamentoEntity(pagamento, pagamentoEntity);

            pagamentosEntities.add(pagamentoEntity);
        }

        return pagamentosEntities;
    }
    //MÉTODO: conversão de ListaEntity para ListaDTO
    public List<Pagamento> conversaoPagamentosDTO(List<PagamentoEntity> pagamentosEntities,List<Pagamento> pagamentos) {

        for(PagamentoEntity pagamentoEntity: pagamentosEntities) {
            Pagamento pagamento = new Pagamento();
            pagamento = conversaoPagamentoDTO(pagamentoEntity, pagamento);

            pagamentos.add(pagamento);
        }
        return pagamentos;
    }

    //MÉTODOS RETORNANDO A ENTITY
    public PagamentoEntity getPagamento(BigInteger idPagamento) {
        Optional<PagamentoEntity> optional = repository.findById(idPagamento);
        return optional.get();

    }
    public List<PagamentoEntity> getPagamentos() {
        return repository.findAll();

    }

    //MÉTODOS RETORNANDO A DTO
    public Pagamento getPagamentoDTO(BigInteger idPagamento) {
        PagamentoEntity pagamentoEntity = getPagamento(idPagamento);
        Pagamento pagamento = new Pagamento();

        pagamento = conversaoPagamentoDTO(pagamentoEntity, pagamento);
        return pagamento;

    }
    public List<Pagamento> getPagamentosDTO() {
        List<PagamentoEntity> pagamentoEntities = getPagamentos();
        List<Pagamento> pagamentos = new ArrayList<>();

        pagamentos = conversaoPagamentosDTO(pagamentoEntities, pagamentos);

        return pagamentos;
    }

    @Transactional
    public String cadastrarPagamento(Pagamento pagamento){

        PagamentoEntity pagamentoEntity = new PagamentoEntity();

      // BigInteger cartaoId = pagamento.getIdCartao();
      //  CartaoEntity cartaoEntity = cartaoRepository.findById(cartaoId).get();

     //   BigInteger contratoId = pagamento.getIdContrato();
     //   ContratoEntity contratoEntity = contratoRepository.findById(contratoId).get();

        pagamentoEntity = conversaoPagamentoEntity(pagamento,  pagamentoEntity);

        repository.save(pagamentoEntity);

        return "Pagamento cadastrado com sucesso";

    }


    @Transactional
    public String alterarPagamento(Pagamento pagamento, BigInteger idPagamento){

        PagamentoEntity pagamentoEntity = getPagamento(idPagamento);
        pagamentoEntity = conversaoPagamentoEntity(pagamento,  pagamentoEntity);

        pagamentoEntity = repository.save(pagamentoEntity);
        return "Alteração realizada com sucesso";

    }

    public String excluirPagamento(BigInteger idPagamento){
        repository.deleteById(idPagamento);
        return "Exclusão de pagamento realizada com sucesso";

    }

    //Grupo2 - Cadastrando Pagamento de agendamento de consulta com Cartao
    @Transactional
    public BigInteger setPagamentoComCartao(PagamentoCartao pagtoCartao){
        PagamentoEntity pagamentoEntity = new PagamentoEntity();
        AgPacienteEntity agPaciente = agPacienteRepository.findById(pagtoCartao.getIdAgPaciente()).get();
        List<CartaoEntity> listaCartoes = new ArrayList<>();
        listaCartoes = cartaoRepository.findByUsuario(agPaciente.getPaciente());
        pagamentoEntity.setIdCartao(listaCartoes.get(0).getIdCartao());
        pagamentoEntity.setAgPacienteEntity(agPacienteRepository.findByIdAgPaciente(pagtoCartao.getIdAgPaciente()).get());
        pagamentoEntity.setIdFormaPgt(BigInteger.valueOf(1));
        pagamentoEntity.setVlPagamento(agPaciente.getAgenda().getMedico().getPreco().getVlConsulta());
        pagamentoEntity.setNrParcela(pagtoCartao.getParcelas());
        repository.save(pagamentoEntity);

        return pagamentoEntity.getIdPagamento();
    }

    //Grupo2 - Cadastrando Pagamento de agendamento de consulta com Plano
    @Transactional
    public BigInteger setPagamentoComPlano(PagamentoPlano pagtoPlano){
        PagamentoEntity pagamentoEntity = new PagamentoEntity();
        pagamentoEntity.setAgPacienteEntity(agPacienteRepository.findByIdAgPaciente(pagtoPlano.getIdAgPaciente()).get());
        pagamentoEntity.setIdFormaPgt(BigInteger.valueOf(3));
        ContratoEntity contrato = contratoRepository.findOneByUsuario(pagamentoEntity.getAgPacienteEntity().getPaciente());
        pagamentoEntity.setIdContrato(contrato.getIdContrato());
        repository.save(pagamentoEntity);


        return pagamentoEntity.getIdPagamento();

    }


}