package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.entity.ContatoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.LojaEntity;
import com.rd.projetointegrador.rdservicesapi.repository.ContatoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    @Autowired
    private LojaRepository lojaRepository;

//    public  List<ContatoEntity>  getContatosLoja(BigInteger id){
//        System.out.println("Contatos para o ID loja: " + id);
//        return repository.findByIdLoja(id);
//    }

    public List<ContatoEntity> getContatosLoja(BigInteger id) {
        System.out.println("Contatos para o ID loja: " + id);
        LojaEntity lojaEntity = lojaRepository.findById(id).get();
        List<ContatoEntity> contatos = repository.findByLoja(lojaEntity);

        return contatos;
    }

}
