package br.com.rd.projetointegrador.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "TB_CATEGORIA")
@Data
public class CategoriaEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    @Column (name = "ID_CATEGORIA")
    private BigInteger idCategoria;

    @Column (name = "DS_CATEGORIA")
    private String dsCategoria;


}
