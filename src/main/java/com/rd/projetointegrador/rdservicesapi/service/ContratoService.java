package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.entity.ContratoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.PlanosEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.ContratoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.PlanosRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContratoService {
    @Autowired
    private ContratoRepository repository;
    @Autowired
    PlanosRepository planosRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    public List<PlanosEntity> getPlanobyUsuario(BigInteger id) {
        UsuarioEntity usuario = usuarioRepository.findById(id).get();
        List<ContratoEntity> contratos = repository.findByUsuario(usuario);
        List<PlanosEntity> listaPlanos = new ArrayList<>();
        for (ContratoEntity contratoEntity : contratos) {
            PlanosEntity plano = contratoEntity.getPlanosEntity();
            listaPlanos.add(plano);
        }
        return listaPlanos;
    }

}
