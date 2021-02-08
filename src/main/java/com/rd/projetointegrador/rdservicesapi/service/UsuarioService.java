package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.*;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    //GRUPO1

    //repositories
    @Autowired private UsuarioRepository repository;
    @Autowired private GeneroRepository generoRepository;
    @Autowired private TipoUsuarioRepository tipoUsuarioRepository;

    //services
    //@Autowired private UfService ufService;
    //@Autowired private EnderecoService enderecoService;


    //MÉTODO: conversão de DTO para Entity
    public UsuarioEntity conversaoUsuarioEntity(Usuario usuario, UsuarioEntity usuarioEntity) {

        GeneroEntity genero = generoRepository.findById(usuario.getIdGenero()).get();
        usuarioEntity.setGenero(genero);

        //TODO: objeto Esp Medica
        usuarioEntity.setIdEspMedica(usuario.getIdEspMedica());

        //TODO: objeto Uf
        usuarioEntity.setIdUfCrm(usuario.getIdUfCrm());

        //TODO: conversao endereços
        //List<EnderecoEntity> enderecosEntities = new ArrayList();
        //enderecosEntities = conversaoEnderecosEntities(usuario.getEnderecos(), enderecosEntities)
        //usuarioEntity.setEnderecos(enderecosEntities);

        TipoUsuarioEntity tipoUsuarioEntity = tipoUsuarioRepository.findById(usuario.getIdTipoUsuario()).get();
        usuarioEntity.setTipoUsuario(tipoUsuarioEntity);

        usuarioEntity.setNmNome(usuario.getNmNome());
        usuarioEntity.setDtNascimento(usuario.getDtNascimento());
        usuarioEntity.setNrCpf(usuario.getNrCpf());
        usuarioEntity.setNrCrm(usuario.getNrCrm());
        usuarioEntity.setDsEndImg(usuario.getDsEndImg());
        usuarioEntity.setIdPreco(usuario.getIdPreco());

        return usuarioEntity;
    }
    //MÉTODO: conversão de Entity para DTO
    public Usuario conversaoUsuarioDTO(UsuarioEntity usuarioEntity, Usuario usuario) {

        //TODO: conversao endereços
        //List<Endereco> enderecos = new ArrayList();
        //enderecos = conversaoEnderecosDTO(usuarioEntity.getEnderecos(), enderecos)
        //usuario.setEnderecos(enderecos);

        usuario.setIdUsuario(usuarioEntity.getIdUsuario());
        usuario.setIdGenero(usuarioEntity.getGenero().getIdGenero());
        usuario.setIdEspMedica(usuarioEntity.getIdEspMedica());
        usuario.setIdUfCrm(usuarioEntity.getIdUfCrm());
        usuario.setIdTipoUsuario(usuarioEntity.getTipoUsuario().getIdTipoUsuario());
        usuario.setNmNome(usuarioEntity.getNmNome());
        usuario.setDtNascimento(usuarioEntity.getDtNascimento());
        usuario.setNrCpf(usuarioEntity.getNrCpf());
        usuario.setNrCrm(usuarioEntity.getNrCrm());
        usuario.setDsEndImg(usuarioEntity.getDsEndImg());
        usuario.setIdPreco(usuarioEntity.getIdPreco());

        return usuario;
    }
    //MÉTODO: conversão ListaDTO para ListaEntity
    public List<UsuarioEntity> conversaoUsuariosEntity(List<Usuario> usuarios, List<UsuarioEntity> usuariosEntity) {

        for(Usuario usuario: usuarios) {
            UsuarioEntity usuarioEntity = new UsuarioEntity();
            usuarioEntity = conversaoUsuarioEntity(usuario, usuarioEntity);

            usuariosEntity.add(usuarioEntity);
        }

        return usuariosEntity;
    }
    //MÉTODO: conversão listaEntity para ListaDTO
    public List<Usuario> conversaoUsuariosDTO(List<UsuarioEntity> usuariosEntities, List<Usuario> usuarios) {

        for(UsuarioEntity usuarioEntity: usuariosEntities) {
            Usuario usuario = new Usuario();
            usuario = conversaoUsuarioDTO(usuarioEntity, usuario);

            usuarios.add(usuario);
        }

        return usuarios;
    }

    //MÉTODOS RETORNANDO A ENTITY
    public UsuarioEntity getUsuario(BigInteger idUsuario) {
        Optional<UsuarioEntity> optional = repository.findById(idUsuario);
        return optional.get();
    }
    public List<UsuarioEntity> getUsuarios() {
        return repository.findAll();

    }
    public List<UsuarioEntity> consultarPorNome(String nmUsuario) {
        return repository.findByNmNome(nmUsuario);
    }
    public List<UsuarioEntity> consultarPorCpf(String nrCpf){
        return repository.findByNrCpf(nrCpf);
    }

    //MÉTODOS RETORNANDO A DTO
    public Usuario getUsuarioDTO(BigInteger idUsuario) {
            UsuarioEntity usuarioEntity = getUsuario(idUsuario);
            Usuario usuario = new Usuario();

            usuario = conversaoUsuarioDTO(usuarioEntity, usuario);

            return usuario;
    }
    public List<Usuario> getUsuariosDTO() {
        List<UsuarioEntity> usuarioEntities = getUsuarios();
        List<Usuario> usuarios = new ArrayList<>();

        usuarios = conversaoUsuariosDTO(usuarioEntities, usuarios);

        return usuarios;
    }

    @Transactional
    public String cadastrarUsuario(Usuario usuario){

        UsuarioEntity usuarioEntity = new UsuarioEntity();

        String cpf = usuarioEntity.getNrCpf();
        List<UsuarioEntity> usuarioExistente = repository.findByNrCpf(cpf);

        if(usuarioExistente.isEmpty()) {

            usuarioEntity = conversaoUsuarioEntity(usuario, usuarioEntity);
            repository.save(usuarioEntity);

            return "Usuário cadastrado com sucesso";
        }

        return "Erro. Cpf já utilizado.";
    }

    @Transactional
    public String alterarUsuario(Usuario usuario, BigInteger idUsuario){

        UsuarioEntity usuarioEntity = getUsuario(idUsuario);
        usuarioEntity = conversaoUsuarioEntity(usuario, usuarioEntity);

        usuarioEntity = repository.save(usuarioEntity);
        return "Alteração realizada com sucesso";
    }

    //EXCLUSÃO OU BLOQUEIO????
    public String excluirUsuario(BigInteger idUsuario){
        repository.deleteById(idUsuario);
        return "Exclusão de usuário realizada com sucesso";

    }



}