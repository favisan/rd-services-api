package com.rd.projetointegrador.rdservicesapi.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name="TB_ESP_MED")
@Data
public class EspMedEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_ESP_MED")
    private BigInteger idEspMed;

    @Column(name = "DS_ESP_MED")
    private String dsEspMed;
}