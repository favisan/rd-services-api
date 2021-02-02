package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Cidade;
import com.rd.projetointegrador.rdservicesapi.dto.Uf;
import com.rd.projetointegrador.rdservicesapi.entity.CidadeEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UfEntity;
import com.rd.projetointegrador.rdservicesapi.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    public Cidade buscarCidadeId(BigInteger id) {
        System.out.println("ID: " + id);

        CidadeEntity entity = repository.findById(id).get();

        Cidade c = new Cidade();

        c.setIdCidade(entity.getIdCidade());
        c.setDsCidade(entity.getDsCidade());
        c.setCdCidadeIbge(entity.getCdCidadeIbge());

        UfEntity ufEntity = entity.getIdUf();
        Uf u = new Uf();
        u.setIdUf(ufEntity.getIdUf());
        u.setDsUf(ufEntity.getDsUf());

        c.setUf(u);

        return c;
    }


    public List<Cidade> listarCidade() {
        List<CidadeEntity> cidadeEntity = repository.findAll();
        List<Cidade> cidades = new ArrayList<>();

        for (CidadeEntity entity : cidadeEntity) {
            Cidade c = new Cidade();
            c.setIdCidade(entity.getIdCidade());
            c.setDsCidade(entity.getDsCidade());
            c.setCdCidadeIbge(entity.getCdCidadeIbge());

            UfEntity ufEntity = entity.getIdUf();
            Uf u = new Uf();
            u.setIdUf(ufEntity.getIdUf());
            u.setDsUf(ufEntity.getDsUf());

            c.setUf(u);

            cidades.add(c);
        }

        return cidades;
    }


    public String cadastrarCidade(Cidade cidade) {
        CidadeEntity entity = new CidadeEntity();
        entity.setDsCidade(cidade.getDsCidade());
        entity.setCdCidadeIbge(cidade.getCdCidadeIbge());

        UfEntity ufEntity = new UfEntity();
        Uf uf = cidade.getUf();
        ufEntity.setIdUf(uf.getIdUf());
        ufEntity.setDsUf(uf.getDsUf());

        entity.setIdUf(ufEntity);

        repository.save(entity);

        return "Cadastro realizado com sucesso!";
    }


    public String alterarCidade(Cidade cidade, BigInteger id) {
        CidadeEntity entity = repository.findById(id).get();
        entity.setDsCidade(cidade.getDsCidade());
        entity.setCdCidadeIbge(cidade.getCdCidadeIbge());

        UfEntity ufEntity = new UfEntity();
        Uf uf = cidade.getUf();

        ufEntity.setIdUf(uf.getIdUf());
        ufEntity.setDsUf(uf.getDsUf());

        entity.setIdUf(ufEntity);

        repository.save(entity);

        return "Alteração realizada com sucesso!";
    }


    public String excluirCidade(BigInteger id) {
        System.out.println("ID: " + id);
        repository.deleteById(id);
        return "Exclusão do ID " + id + " realizada com sucesso!";
    }


    public List<CidadeEntity> consultarPorNome(String dsCidade) {
        return repository.findByDsCidade(dsCidade);
    }

}