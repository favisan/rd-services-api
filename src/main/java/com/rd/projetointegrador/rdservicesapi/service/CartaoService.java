package com.rd.projetointegrador.rdservicesapi.service;


import com.rd.projetointegrador.rdservicesapi.dto.Cartao;
import com.rd.projetointegrador.rdservicesapi.entity.CartaoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.CartaoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;



    public List<CartaoEntity> verCartoes() {
        List<CartaoEntity> cartoes = repository.findAll();
        return cartoes;
    }

    public CartaoEntity verCartao(BigInteger id) {

        Optional<CartaoEntity> optional = repository.findById(id);
        CartaoEntity cartaoEntity = optional.get();

        return cartaoEntity;
    }

/*
    @Transactional
    public String cadastrarCartao(Cartao cartao) {

        CartaoEntity cartaoEntity = new CartaoEntity();

        BigInteger usuarioId = cartao.getIdUsuario();
        UsuarioEntity usuarioEntity = usuarioRepository.findById(usuarioId).get();

        cartaoEntity.setIdCartao(cartao.getIdCartao());
        cartaoEntity.setNrCartao(cartao.getNrCartao());
        cartaoEntity.setCodSeguranca(cartao.getCodSeguranca());
        cartaoEntity.setDtValidade(cartao.getDtValidade());
        cartaoEntity.setDtEmissao(cartao.getDtEmissao());
        cartaoEntity.setIdPaciente(cartao.getIdPaciente());
        cartaoEntity.setIdUsuario(usuarioEntity);
         repository.save(cartaoEntity);

        System.out.println(cartao.getIdCartao() + " . " + cartao.getNrCartao() + " . " + cartao.getCodSeguranca() + " . " + cartao.getDtValidade() + " . " + cartao.getDtEmissao() + " . " + cartao.getIdPaciente());

        return "Cartao Cadastrado com sucesso";

    }

    @Transactional
    public String alterarCartao(Cartao cartao, BigInteger idCartao) {

        CartaoEntity cartaoEntity = new CartaoEntity();



        BigInteger usuarioId = cartao.getIdUsuario();
        UsuarioEntity usuarioEntity = usuarioRepository.findById(usuarioId).get();

        cartaoEntity.setIdCartao(cartao.getIdCartao());
        cartaoEntity.setNrCartao(cartao.getNrCartao());
        cartaoEntity.setCodSeguranca(cartao.getCodSeguranca());
        cartaoEntity.setDtValidade(cartao.getDtValidade());
        cartaoEntity.setDtEmissao(cartao.getDtEmissao());
        cartaoEntity.setIdPaciente(cartao.getIdPaciente());
        cartaoEntity.setIdUsuario(usuarioEntity);

        repository.save( cartaoEntity);
        return "Alteração  de cartão realizada com sucesso";
    }


    public String excluirCartao(BigInteger idCartao) {
        repository.deleteById(idCartao);
        return "Exclusão do cartão realizada com sucesso";

    }

 */

}