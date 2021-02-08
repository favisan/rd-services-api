package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.ServicoPlano;
import com.rd.projetointegrador.rdservicesapi.entity.ServicoPlanoEntity;
import com.rd.projetointegrador.rdservicesapi.repository.ServicoPlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoPlanoService {

    @Autowired
    private ServicoPlanoRepository repository;

    //MÉTODOS RETORNANDO A ENTITY
    public ServicoPlanoEntity getServicoPlano(BigInteger idServicoPlano) {
        System.out.println("IdServPlano: " + idServicoPlano);
        Optional<ServicoPlanoEntity> optional = repository.findById(idServicoPlano);
        return optional.get();

    }
    public List<ServicoPlanoEntity> getServicosPlano() {
        return repository.findAll();
    }

    //MÉTODO RETORNANDO AS DTOs
    public List<ServicoPlano> getServicosPlanoDTO() {
        List<ServicoPlanoEntity> servsPlanoEntities = getServicosPlano();
        List<ServicoPlano> servicosPlano = new ArrayList<>();

        for(ServicoPlanoEntity servPlanoEntity: servsPlanoEntities){
            ServicoPlano servPlano = new ServicoPlano();
            servPlano.setIdServicoPlano(servPlanoEntity.getIdServicoPlano());
            servPlano.setDsServico(servPlano.getDsServico());

            servicosPlano.add(servPlano);
        }

        return servicosPlano;
    }

    @Transactional
    public String cadastrarServicoPlano(ServicoPlano servico){

        ServicoPlanoEntity servicoPlanoEntity = new ServicoPlanoEntity();
        servicoPlanoEntity.setDsServico(servico.getDsServico());
        repository.save(servicoPlanoEntity);


        return "Serviço cadastrado com sucesso";

    }

    @Transactional
    public String alterarServicoPlano(ServicoPlano servico, BigInteger idServicoPlano) {

        ServicoPlanoEntity servicoPlanoEntity = getServicoPlano(idServicoPlano);
        servicoPlanoEntity.setDsServico(servico.getDsServico());
        servicoPlanoEntity = repository.save(servicoPlanoEntity);
        return "Alteração realizada com sucesso";
    }

    public String excluirServicoPlano(BigInteger idServicoPlano){
        repository.deleteById(idServicoPlano);
        return "Exclusão de serviço realizada com sucesso";

    }

}


