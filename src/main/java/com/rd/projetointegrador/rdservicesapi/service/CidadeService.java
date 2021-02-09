package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Cidade;
import com.rd.projetointegrador.rdservicesapi.dto.Uf;
import com.rd.projetointegrador.rdservicesapi.entity.CidadeEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UfEntity;
import com.rd.projetointegrador.rdservicesapi.repository.CidadeRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    @Autowired
    private UfRepository ufRepository;

    public Cidade buscarCidadeId(BigInteger id) {
        System.out.println("ID: " + id);

        CidadeEntity entity = repository.findById(id).get();

        Cidade c = new Cidade();

        c.setIdCidade(entity.getIdCidade());
        c.setDsCidade(entity.getDsCidade());
        c.setCdCidadeIbge(entity.getCdCidadeIbge());

        UfEntity ufEntity = entity.getUf();
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

            UfEntity ufEntity = entity.getUf();
            Uf u = new Uf();
            u.setIdUf(ufEntity.getIdUf());
            u.setDsUf(ufEntity.getDsUf());

            c.setUf(u);

            cidades.add(c);
        }

        return cidades;
    }

    public List<Cidade> buscarCidadePorUf(BigInteger idUf) {

        UfEntity ufEntity = ufRepository.findById(idUf).get();
        List<CidadeEntity> cidades = repository.findByUf(ufEntity);

        List<Cidade> cidade = new ArrayList<>();

        for (CidadeEntity cid : cidades) {
            Cidade c = new Cidade();
            c.setIdCidade(cid.getIdCidade());
            c.setDsCidade(cid.getDsCidade());

            cidade.add(c);
        }
        return cidade;
    }
}
