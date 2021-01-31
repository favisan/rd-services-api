package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Planos;
import com.rd.projetointegrador.rdservicesapi.dto.ServicoPlano;
import com.rd.projetointegrador.rdservicesapi.entity.PlanosEntity;
import com.rd.projetointegrador.rdservicesapi.entity.ServicoPlanoEntity;
import com.rd.projetointegrador.rdservicesapi.repository.PlanosRepository;
import com.rd.projetointegrador.rdservicesapi.repository.ServicoPlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanosService {

    @Autowired
    private PlanosRepository repository;
    @Autowired
    private ServicoPlanoRepository servRepository;

    public PlanosEntity getPlano(BigInteger idPlano) {
        System.out.println("IdPlano: " + idPlano);
        Optional<PlanosEntity> optional = repository.findById(idPlano);
        return optional.get();

    }

    public List<PlanosEntity> getPlanos(BigInteger idPlano) {
        return repository.findAll();

    }

    @Transactional
    public String cadastrarPlano(Planos plano){

        PlanosEntity planosEntity = new PlanosEntity();

        planosEntity.setNmPlano(plano.getNmPlano());
        planosEntity.setDsPlano(plano.getDsPlano());
        planosEntity.setVlPlano(plano.getVlPlano());
        planosEntity.setIdServicoPlano(plano.getIdServicoPlano());

        repository.save(planosEntity);

        System.out.println(plano.getIdPlano() + " . " + plano.getNmPlano() + " . " +plano.getDsPlano() + " . " + plano.getVlPlano());

        return "Plano cadastrado com sucesso";

    }

    @Transactional
    public String alterarPlano(Planos plano, BigInteger idPlano){

        PlanosEntity planoEntity = getPlano(idPlano);

        planoEntity.setNmPlano(plano.getNmPlano());
        planoEntity.setDsPlano(plano.getDsPlano());
        planoEntity.setVlPlano(plano.getVlPlano());
        //planoEntity.setIdServicoPlano(plano.getIdServicoPlano());

        List<ServicoPlanoEntity> listaServPlano = new ArrayList<>();
        for(ServicoPlano servico : plano.getServicos()){

            BigInteger idServicoPlano = servico.getIdServicoPlano();
            Optional<ServicoPlanoEntity> optional = servRepository.findById(idServicoPlano);
            ServicoPlanoEntity servPlanoEntity = optional.get();
            listaServPlano.add(servPlanoEntity);
        }

        planoEntity.setServicos(listaServPlano);

        planoEntity = repository.save(planoEntity);
        return "Alteração realizada com sucesso";
    }

    public String excluirPlano(BigInteger idPlano){
        repository.deleteById(idPlano);
        return "Exclusão de plano realizada com sucesso";

    }


}
