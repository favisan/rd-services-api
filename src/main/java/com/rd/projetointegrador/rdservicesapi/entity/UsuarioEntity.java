package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Data
@Table (name="TB_USUARIO")
public class UsuarioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name= "ID_USUARIO")
    BigInteger idUsuario;

    @Column(name="ID_GENERO")
    BigInteger idGenero;

    @Column(name="ID_ESP_MED")
    BigInteger idEspMedica;

    @Column(name= "ID_UF_CRM")
    BigInteger idUfCrm;

    @Column(name="ID_TIPO_USUARIO")
    BigInteger idTipoUsuario;

    @Column(name="NM_USUARIO")
    String nmUsuario;

    @Column(name="DT_NASCIMENTO")
    Date dtNascimento;

    @Column(name="NR_CPF")
    String nrCpf;

    @Column(name="NR_CRM")
    String nrCrm;

    @Column(name="DS_END_IMG")
    String dsEndImg;
}
