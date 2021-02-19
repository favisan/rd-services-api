package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.*;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicoService {

    //Services
    @Autowired private UfService ufService;
    @Autowired private EnderecoService enderecoService;
    @Autowired private CidadeService cidadeService;
    @Autowired private ContatoService contatoService;
    @Autowired private EspMedService espMedService;
    @Autowired private PrecoService precoService;

    //MÉTODO: conversão de Entity para DTO
    public OutputMedico conversaoOutputMedicoDTO(UsuarioEntity usuarioEntity, OutputMedico medico) {

        UfEntity ufEntity = usuarioEntity.getUf();
        Uf uf = new Uf();
        uf = ufService.conversaoUfDTO(ufEntity, uf);

        EspMedEntity espMedEntity= usuarioEntity.getEspMed();
        EspMed espMed = new EspMed();
        espMed = espMedService.conversaoEspMedDTO(espMedEntity, espMed);

        PrecoEntity precoEntity = usuarioEntity.getPreco();
        Preco preco = new Preco();
        preco = precoService.conversaoPrecoDTO(precoEntity, preco);

        List<Endereco> enderecos = new ArrayList();
        enderecos = enderecoService.conversaoEnderecosDTO(usuarioEntity.getEnderecos(), enderecos);

        List<Contato> contatos = new ArrayList();
        contatos = contatoService.conversaoContatosDTO(usuarioEntity.getContatos(), contatos);

        medico.setIdUsuario(usuarioEntity.getIdUsuario());
        medico.setUfCrm(uf);
        medico.setEspMed(espMed);
        medico.setPreco(preco);
        medico.setNome(usuarioEntity.getNmNome());
        medico.setNrCpf(usuarioEntity.getNrCpf());
        medico.setDtNascimento(usuarioEntity.getDtNascimento());
        medico.setNrCrm(usuarioEntity.getNrCrm());
        medico.setEnderecos(enderecos);
        medico.setContatos(contatos);

        return medico;
    }

}
