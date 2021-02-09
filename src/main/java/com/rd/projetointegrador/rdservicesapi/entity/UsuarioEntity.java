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
    //GRUPO1
    //TODO: usar as entitdades das outras tabelas ao invés de biginteger.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name= "ID_USUARIO")
    BigInteger idUsuario;

    @ManyToOne
    @JoinColumn(name = "ID_GENERO")
    private GeneroEntity genero;

    @Column(name="ID_ESP_MED")
    private BigInteger idEspMedica;

    @Column(name= "ID_UF_CRM")
    private BigInteger idUfCrm;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_USUARIO")
    private TipoUsuarioEntity tipoUsuario;

    @Column(name="NM_NOME")
    private String nmNome;

    @Column(name="DT_NASCIMENTO")
    private Date dtNascimento;

    @Column(name="NR_CPF")
    private String nrCpf;

    @Column(name="NR_CRM")
    private String nrCrm;

    @Column(name="DS_END_IMG")
    private String dsEndImg;

    @Column(name="ID_PRECO")
    private BigInteger idPreco;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "TB_USUARIO_ENDERECO",
//            joinColumns = @JoinColumn(name = "ID_USUARIO"),
//            inverseJoinColumns = @JoinColumn(name = "ID_ENDERECO")
//    )
//    private List<EnderecoEntity> enderecos;



}
