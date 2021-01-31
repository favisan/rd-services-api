package com.rd.projetointegrador.rdservicesapi.dto;

import com.rd.projetointegrador.rdservicesapi.entity.MedicacaoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.ProntuarioEntity;
import com.rd.projetointegrador.rdservicesapi.entity.TipoReceitaEntity;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class Receituario {

    private BigInteger idReceituario;

//    private UsuarioEntity paciente;

    private ProntuarioEntity prontuario;

//    private UsuarioEntity medico;

    private TipoReceitaEntity tipoReceita;

    private BigInteger idViaAdm;

    private BigInteger idFormaFarmac;

    private Integer vlQuantidade;

    private Float vlConcentracao;

    private String ds;

    private Date dtEmissao;

    private String dsEndImgAssMed;

    private List<MedicacaoEntity> medicamentos;

}
