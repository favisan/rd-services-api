package com.rd.projetointegrador.rdservicesapi.service;


import com.rd.projetointegrador.rdservicesapi.dto.Pagamento;
import com.rd.projetointegrador.rdservicesapi.entity.CartaoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.ContratoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.PagamentoEntity;
import com.rd.projetointegrador.rdservicesapi.repository.CartaoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.ContratoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired private PagamentoRepository repository;

    @Autowired private CartaoRepository cartaoRepository;

    @Autowired private ContratoRepository contratoRepository;
    public PagamentoEntity getPagamento(BigInteger idPagamento) {
        Optional<PagamentoEntity> optional = repository.findById(idPagamento);
        return optional.get();

    }

    public List<PagamentoEntity> getPagamentos(BigInteger idPagamento) {
        return repository.findAll();

    }

        @Transactional
        public String cadastrarPagamento(Pagamento pagamento){

            PagamentoEntity pagamentoEntity = new PagamentoEntity();

          // BigInteger cartaoId = pagamento.getIdCartao();
          //  CartaoEntity cartaoEntity = cartaoRepository.findById(cartaoId).get();

         //   BigInteger contratoId = pagamento.getIdContrato();
         //   ContratoEntity contratoEntity = contratoRepository.findById(contratoId).get();


            pagamentoEntity.setIdContrato(pagamento.getIdContrato());
            pagamentoEntity.setIdFormaPgt(pagamento.getIdFormaPgt());
            pagamentoEntity.setIdNF(pagamento.getIdNF());
            pagamentoEntity.setIdAgPaciente(pagamento.getIdAgPaciente());
            pagamentoEntity.setIdPedido(pagamento.getIdPedido());
            pagamentoEntity.setVlPagamento(pagamento.getVlPagamento());
            pagamentoEntity.setDtPagamento(pagamento.getDtPagamento());
            pagamentoEntity.setNrParcela(pagamento.getNrParcela());
            pagamentoEntity.setIdCartao(pagamento.getIdCartao());
           // pagamentoEntity.setIdCartao(cartaoEntity);

            repository.save(pagamentoEntity);

            System.out.println(pagamento.getIdCartao() + " . " + pagamento.getIdContrato() + " . " +pagamento.getIdFormaPgt() + " . " + pagamento.getIdNF() + " . " +pagamento.getNrParcela()+ " . " +
                    pagamento.getIdAgPaciente() + " . " + pagamento.getIdPedido()+ " . " +pagamento.getVlPagamento()+ " . " +pagamento.getDtPagamento());

            return "Pagamento cadastrado com sucesso";

        }

        @Transactional
        public String alterarPagamento(Pagamento pagamento, BigInteger idPagamento){

            PagamentoEntity pagamentoEntity = getPagamento(idPagamento);

          //  BigInteger cartaoId = pagamento.getIdCartao();
           // CartaoEntity cartaoEntity = cartaoRepository.findById(cartaoId).get();

           // BigInteger contratoId = pagamento.getIdContrato();
           // ContratoEntity contratoEntity = contratoRepository.findById(contratoId).get();

           // pagamentoEntity.setIdContrato(contratoEntity);
            pagamentoEntity.setIdContrato(pagamento.getIdContrato());
            pagamentoEntity.setIdFormaPgt(pagamento.getIdFormaPgt());
            pagamentoEntity.setIdNF(pagamento.getIdNF());
            pagamentoEntity.setIdAgPaciente(pagamento.getIdAgPaciente());
            pagamentoEntity.setIdPedido(pagamento.getIdPedido());
            pagamentoEntity.setVlPagamento(pagamento.getVlPagamento());
            pagamentoEntity.setDtPagamento(pagamento.getDtPagamento());
            pagamentoEntity.setNrParcela(pagamento.getNrParcela());
          //  pagamentoEntity.setIdCartao(cartaoEntity);
            pagamentoEntity.setIdCartao(pagamento.getIdCartao());

            pagamentoEntity = repository.save(pagamentoEntity);
            return "Alteração realizada com sucesso";

        }

    public String excluirPagamento(BigInteger idPagamento){
        repository.deleteById(idPagamento);
        return "Exclusão de pagamento realizada com sucesso";

    }


}