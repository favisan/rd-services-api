package br.com.rd.projetointegrador.service;

import br.com.rd.projetointegrador.entity.ReceitaEntity;
import br.com.rd.projetointegrador.repository.CategoriaRepository;
import br.com.rd.projetointegrador.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<ReceitaEntity> consultarReceitas(String nomeReceita){
        System.out.println("Nome Receita: " + nomeReceita);
        return receitaRepository.findByNomeReceita(nomeReceita, nomeReceita);
    }

    public ReceitaEntity consultarReceita(BigInteger id){

        return receitaRepository.findById(id).get();
    }



}
