package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@Table(name = "TB_NF")
public class NfEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //na tb nao esta como auto incremento
    @Column(name = "ID_NF", nullable = false)
    private BigInteger idNf;

    @Column(name = "NR_NF", nullable = false)
    private String nrNf;

    @Column(name = "ID_PACIENTE")
    private BigInteger idPaciente;

    @Column(name = "ID_MEDICO")
    private BigInteger idMedico;

    @Column(name = "NR_SERIE", nullable = false)
    private BigInteger nrSerie;

    @Column(name = "DT_EMISSAO", nullable = false)
    private DateTimeFormatter dtEmissao;

    @Column(name = "CDS_CHAVE_ACESSO", nullable = false)
    private String cdsChaveAcesso;

    @Column(name = "VL_NOTA", nullable = false)
    private Float vlNota;

}
