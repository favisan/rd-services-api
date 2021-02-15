package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Cartao;
import com.rd.projetointegrador.rdservicesapi.dto.Usuario;
import com.rd.projetointegrador.rdservicesapi.repository.CartaoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rd.projetointegrador.rdservicesapi.entity.CartaoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Optional;

@Service
public class CartaoService {
    //GRUPO1

    @Autowired private CartaoRepository repository;
    @Autowired private UsuarioRepository usuarioRepository;

    @Autowired private UsuarioService usuarioService;

    SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat SDF2 = new SimpleDateFormat("yyyy-MM-dd");

    //MÉTODO: conversão de DTO para Entity
    public CartaoEntity conversaoCartaoEntity(Cartao cartao, CartaoEntity cartaoEntity) {

        UsuarioEntity usuarioEntity = usuarioRepository.findById(cartao.getUsuario().getIdUsuario()).get();

        try {
        cartaoEntity.setNrCartao(cartao.getNrCartao());
        cartaoEntity.setCodSeguranca(cartao.getCodSeguranca());

        Date dataValidade = SDF2.parse(cartao.getDtValidade());
        cartaoEntity.setDtValidade(dataValidade);
        Date dataEmissao = SDF2.parse(cartao.getDtEmissao());
        cartaoEntity.setDtEmissao(dataEmissao);

        cartaoEntity.setNmNome(cartao.getNmNome());
        cartaoEntity.setUsuario(usuarioEntity);
            return cartaoEntity;
        } catch(ParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    //MÉTODO: conversão de Entity para DTO
    public Cartao conversaoCartaoDTO(CartaoEntity cartaoEntity, Cartao cartao) {

        cartao.setNrCartao(cartaoEntity.getNrCartao());
        cartao.setCodSeguranca(cartaoEntity.getCodSeguranca());

        String dtValidade = SDF.format(cartaoEntity.getDtValidade());
        cartao.setDtValidade(dtValidade);
        String dtEmissao = SDF.format(cartaoEntity.getDtEmissao());
        cartao.setDtEmissao(dtEmissao);
        cartao.setNmNome( cartaoEntity.getNmNome());

        Usuario usuario = new Usuario();
        usuario = usuarioService.conversaoUsuarioDTO(cartaoEntity.getUsuario(),usuario);
        cartao.setUsuario(usuario);

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

    //GRUPO2
    public List<CartaoEntity> getCartaobyUsuario(UsuarioEntity usuario) {
        List<CartaoEntity> listaCartoes = repository.findByUsuario(usuario);
        return listaCartoes;
    }

    @Transactional
    public String cadastrarCartao(Cartao cartao) {

        CartaoEntity cartaoEntity = new CartaoEntity();

        repository.save(cartaoEntity);

        if(usuarioRepository.existsById(cartao.getUsuario().getIdUsuario())) {
        repository.save(cartaoEntity);
                return "Cartao Cadastrado com sucesso";
            }

            return "Erro ao cadastrar cartão";
    }

    @Transactional
    public String alterarCartao(Cartao cartao, BigInteger idCartao) {
        CartaoEntity cartaoEntity = repository.findById(idCartao).get();

        cartaoEntity = conversaoCartaoEntity(cartao, cartaoEntity);

        repository.save(cartaoEntity);
        return "Alteração  de cartão realizada com sucesso";
    }

    public String excluirCartao(BigInteger idCartao) {
        repository.deleteById(idCartao);
        return "Exclusão do cartão realizada com sucesso";
    }
}