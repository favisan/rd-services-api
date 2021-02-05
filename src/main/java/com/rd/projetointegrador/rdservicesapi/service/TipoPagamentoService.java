package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.TipoPagamento;
import com.rd.projetointegrador.rdservicesapi.entity.TipoPagamentoEntity;
import com.rd.projetointegrador.rdservicesapi.repository.TipoPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class TipoPagamentoService {

    @Autowired private TipoPagamentoRepository repository;

    public TipoPagamentoEntity getTipoPagamento(BigInteger idFormaPagamento) {
        Optional<TipoPagamentoEntity> optional = repository.findById(idFormaPagamento);
        return optional.get();

    }
    public List<TipoPagamentoEntity> getTiposPagamentos() {
        return repository.findAll();
    }

    @Transactional
    public String cadastrarTipoPagamento(TipoPagamento tipoPagamento){

        TipoPagamentoEntity tipoPagamentoEntity = new TipoPagamentoEntity();
        tipoPagamentoEntity.setDsFormaPagamento(tipoPagamento.getDsFormaPagamento());

        repository.save(tipoPagamentoEntity);

        System.out.println(tipoPagamento.getIdFormaPagamento() + " . " + tipoPagamento.getDsFormaPagamento() );

        return "Tipo Pagamento cadastrado com sucesso";
    }

    @Transactional
    public String alterarTipoPagamento(TipoPagamento tipoPagamento, BigInteger idFormaPagamento){

        TipoPagamentoEntity tipoPagamentoEntity =  getTipoPagamento(idFormaPagamento);
        tipoPagamentoEntity.setDsFormaPagamento(tipoPagamento.getDsFormaPagamento());

        repository.save(tipoPagamentoEntity);

        return "Pagamento Tipo alterado com sucesso";

    }

    public String excluirTipoPagamento(BigInteger idFormaPagamento){
        repository.deleteById(idFormaPagamento);
        return "Exclusão do Pagamento Tipo realizada com sucesso";

    }
}

