package com.rd.projetointegrador.rdservicesapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultData<T> {

    private Integer status;

    private String mensagem;

    private T retorno;

    private Number tipoUsuario;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")

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

    public ResultData(Integer status, String mensagem, T retorno, Number tipoUsuario){
        this.status = status;
        this.mensagem = mensagem;
        this.retorno = retorno;
        this.tipoUsuario = tipoUsuario;
    }

}
