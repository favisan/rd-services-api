package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Servico;
import com.rd.projetointegrador.rdservicesapi.entity.ServicoEntity;
import com.rd.projetointegrador.rdservicesapi.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;


    public ServicoEntity getServico(BigInteger id){
        System.out.println("ID: " + id);
        Optional<ServicoEntity> optional = repository.findById(id);
        ServicoEntity entity = optional.get();

        return entity;
    }

    public List<ServicoEntity> getServicos2(){

        return repository.findAll();
    }

    public List<Servico> getServicos(){

        List<ServicoEntity> servicosEntities = repository.findAll();

        List<Servico> servicosDTO = new ArrayList<>();

        for(ServicoEntity servicoEntity : servicosEntities){
            Servico servicoDTO = new Servico();
            servicoDTO.setId(servicoEntity .getId().longValue());
            servicoDTO.setNome(servicoEntity.getNome());
            servicoDTO.setPreco(servicoEntity.getPreco());

            servicosDTO.add(servicoDTO);
        }
        return servicosDTO;
    }

    @Transactional
    public String cadastrar(Servico servico){

        ServicoEntity entity = new ServicoEntity();
        entity.setNome(servico.getNome());
        entity.setPreco(servico.getPreco());

        repository.save(entity);

        System.out.println(servico.getNome() + " . " + servico.getPreco());

        return "Serviço cadastrado com sucesso!";
    }

    @Transactional
    public String alterar(Servico servico, BigInteger id) {

        Optional<ServicoEntity> op = repository.findById(id);
        ServicoEntity entity = op.get();

        entity.setNome(servico.getNome());
        entity.setPreco(servico.getPreco());

        repository.save(entity);
        return "Atualizado com sucesso";
    }

    public String excluir (BigInteger id){
        System.out.println("ID: " + id);
        repository.deleteById(id);
        return "Exclusão do ID " + id + " foi realizado com sucesso";
    }
}
