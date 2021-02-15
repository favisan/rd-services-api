package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Preco;
import com.rd.projetointegrador.rdservicesapi.entity.PrecoEntity;
import org.springframework.stereotype.Service;

@Service
public class PrecoService {

    //MÉTODO: conversão de Entity para DTO
    public Preco conversaoPrecoDTO(PrecoEntity precoEntity, Preco preco) {

        preco.setIdPreco(precoEntity.getIdPreco());
        preco.setVlConsulta(precoEntity.getVlConsulta());

        return preco;
    }

}
