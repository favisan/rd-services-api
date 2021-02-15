package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultData<T> {

    private Integer status;

    private String mensagem;

    private T retorno;

    private Date dtTimestampErro = new Date();

    public ResultData(Integer status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

    public ResultData(Integer status, String mensagem, T retorno){
        this.status = status;
        this.mensagem = mensagem;
        this.retorno = retorno;
    }

}