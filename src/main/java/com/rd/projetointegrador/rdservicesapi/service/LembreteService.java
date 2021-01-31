package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Lembrete;
import com.rd.projetointegrador.rdservicesapi.entity.LembreteEntity;
import com.rd.projetointegrador.rdservicesapi.repository.LembreteRepository;
import com.rd.projetointegrador.rdservicesapi.repository.LembreteServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public class LembreteService {
    @Autowired
    private LembreteRepository repository;
    @Autowired
    private LembreteServicoRepository lsrepository;

    //TODO: Precisa retornar o DTO
    public LembreteEntity getLembrete(BigInteger idLembrete) {
        System.out.println("IdLembrete: " + idLembrete);
        Optional<LembreteEntity> optional = repository.findById(idLembrete);
        return optional.get();

    }

    //TODO: Precisa retornar o DTO
    public List<LembreteEntity> getLembretes(BigInteger idLembrete) {
        return repository.findAll();

    }

    @Transactional
    public String cadastrarLembrete(Lembrete lembrete){

        LembreteEntity lembreteEntity = new LembreteEntity();

        lembreteEntity.setDsLembrete(lembrete.getDsLembrete());
        //TODO: atributos na entity

        repository.save(lembreteEntity);

        return "Lembrete cadastrado com sucesso";

    }

    @Transactional
    public String alterarLembrete(Lembrete Lembrete, BigInteger idLembrete){

        LembreteEntity LembreteEntity = getLembrete(idLembrete);
        //TODO: atributos na entity

        LembreteEntity = repository.save(LembreteEntity);
        return "Alteração realizada com sucesso";
    }

    public String excluirLembrete(BigInteger idLembrete){
        repository.deleteById(idLembrete);
        return "Exclusão de Lembrete realizada com sucesso";

    }
}
