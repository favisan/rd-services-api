package com.rd.projetointegrador.rdservicesapi.service;



import com.rd.projetointegrador.rdservicesapi.dto.Cartao;
import com.rd.projetointegrador.rdservicesapi.entity.CartaoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.ContratoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.CartaoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public CartaoEntity getCartao(BigInteger idCartao) {
        Optional<CartaoEntity> optional = repository.findById(idCartao);
        return optional.get();

    }

    public List<CartaoEntity> getCartoes(BigInteger idCartao) {
        return repository.findAll();

    }




    @Transactional
    public String cadastrarCartao(Cartao cartao) {

        CartaoEntity cartaoEntity = new CartaoEntity();

        BigInteger usuarioId = cartao.getIdUsuario();
        UsuarioEntity usuarioEntity = usuarioRepository.findById(usuarioId).get();


        cartaoEntity.setNrCartao(cartao.getNrCartao());
        cartaoEntity.setCodSeguranca(cartao.getCodSeguranca());
        cartaoEntity.setDtValidade(cartao.getDtValidade());
        cartaoEntity.setDtEmissao(cartao.getDtEmissao());
        cartaoEntity.setIdUsuario(usuarioEntity);
        repository.save(cartaoEntity);

        System.out.println(cartao.getIdCartao() + " . " + cartao.getNrCartao() + " . " + cartao.getCodSeguranca() + " . " + cartao.getDtValidade() + " . " + cartao.getDtEmissao());

        return "Cartao Cadastrado com sucesso";

    }
/*
    @Transactional
    public String alterarCartao(Cartao cartao, BigInteger idCartao) {
        CartaoEntity cartaoEntity = new CartaoEntity();
        BigInteger usuarioId = cartao.getIdUsuario();
        UsuarioEntity usuarioEntity = usuarioRepository.findById(usuarioId).get();
        cartaoEntity.setNrCartao(cartao.getNrCartao());
        cartaoEntity.setCodSeguranca(cartao.getCodSeguranca());
        cartaoEntity.setDtValidade(cartao.getDtValidade());
        cartaoEntity.setDtEmissao(cartao.getDtEmissao());
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