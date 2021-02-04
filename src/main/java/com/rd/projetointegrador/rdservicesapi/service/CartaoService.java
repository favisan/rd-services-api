package com.rd.projetointegrador.rdservicesapi.service;



import com.rd.projetointegrador.rdservicesapi.dto.Cartao;
import com.rd.projetointegrador.rdservicesapi.dto.Usuario;
import com.rd.projetointegrador.rdservicesapi.entity.*;
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

    @Autowired private CartaoRepository repository;
    @Autowired private UsuarioRepository usuarioRepository;

    //MÉTODO: conversão de DTO para Entity
    public CartaoEntity conversaoCartaoEntity(Cartao cartao, CartaoEntity cartaoEntity) {

        UsuarioEntity usuarioEntity = usuarioRepository.findById(cartao.getIdUsuario()).get();

        cartaoEntity.setNrCartao(cartao.getNrCartao());
        cartaoEntity.setCodSeguranca(cartao.getCodSeguranca());
        cartaoEntity.setDtValidade(cartao.getDtValidade());
        cartaoEntity.setDtEmissao(cartao.getDtEmissao());
        cartaoEntity.setUsuario(usuarioEntity);

        return cartaoEntity;
    }

    //MÉTODO: conversão de Entity para DTO
    public Cartao conversaoCartaoDTO(CartaoEntity cartaoEntity, Cartao cartao) {

        cartao.setNrCartao(cartaoEntity.getNrCartao());
        cartao.setCodSeguranca(cartaoEntity.getCodSeguranca());
        cartao.setDtValidade(cartaoEntity.getDtValidade());
        cartao.setDtEmissao(cartaoEntity.getDtEmissao());
        cartao.setIdUsuario(cartaoEntity.getUsuario().getIdUsuario());

        return cartao;
    }


    //MÉTODOS RETORNANDO A ENTITY
    public CartaoEntity getCartao(BigInteger idCartao) {
        Optional<CartaoEntity> optional = repository.findById(idCartao);
        return optional.get();

    }
    public List<CartaoEntity> getCartoes() {
        return repository.findAll();

    }
    public List<CartaoEntity> getCartaoByUsuario(BigInteger idUsuario) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(idUsuario).get();
        return repository.findByUsuario(usuarioEntity);

    }

    @Transactional
    public String cadastrarCartao(Cartao cartao) {

        CartaoEntity cartaoEntity = new CartaoEntity();

        repository.save(cartaoEntity);

        System.out.println(cartao.getIdCartao() + " . " + cartao.getNrCartao() + " . " + cartao.getCodSeguranca() + " . " + cartao.getDtValidade() + " . " + cartao.getDtEmissao());

        return "Cartao Cadastrado com sucesso";

    }

    @Transactional
    public String alterarCartao(Cartao cartao, BigInteger idCartao) {
        CartaoEntity cartaoEntity = repository.findById(idCartao).get();

        cartaoEntity = conversaoCartaoEntity(cartao, cartaoEntity);

        repository.save( cartaoEntity);
        return "Alteração  de cartão realizada com sucesso";
    }

    public String excluirCartao(BigInteger idCartao) {
        repository.deleteById(idCartao);
        return "Exclusão do cartão realizada com sucesso";
    }

}
