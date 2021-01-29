package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.ServicoPlano;
import com.rd.projetointegrador.rdservicesapi.entity.ServicoPlanoEntity;
import com.rd.projetointegrador.rdservicesapi.repository.ServicoPlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoPlanoService {

    @Autowired
    private ServicoPlanoRepository repository;

    public ServicoPlanoEntity getServicoPlano(BigInteger idServicoPlano) {
        System.out.println("IdServPlano: " + idServicoPlano);
        Optional<ServicoPlanoEntity> optional = repository.findById(idServicoPlano);
        return optional.get();

    }

    public List<ServicoPlanoEntity> getServicosPlano(BigInteger idServicoPlano) {
        return repository.findAll();

    }

    @Transactional
    public String cadastrarServicoPlano(ServicoPlano servico){

        ServicoPlanoEntity servicoPlanoEntity = new ServicoPlanoEntity();

        servicoPlanoEntity.setDsServico(servico.getDsServico());

        repository.save(servicoPlanoEntity);

        System.out.println(servico.getIdServicoPlano() + " . " + servico.getDsServico() + " . " );

        return "Serviço cadastrado com sucesso";

    }

    @Transactional
    public String alterarServicoPlano(ServicoPlano servico, BigInteger idServicoPlano) {

        ServicoPlanoEntity servicoPlanoEntity = getServicoPlano(idServicoPlano);

        servicoPlanoEntity.setDsServico(servico.getDsServico());

        servicoPlanoEntity = repository.save(servicoPlanoEntity);
        return "Alteração realizado com sucesso";
    }

    public String excluirServicoPlano(BigInteger idServicoPlano){
        repository.deleteById(idServicoPlano);
        return "Exclusão de serviço realizado com sucesso";

    }

}


