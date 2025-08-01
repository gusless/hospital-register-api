package com.med.gus.api.domain.address;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(){}

    public Endereco(String logradouro, String bairro, String cep, String numero, String complemento, String cidade, String uf) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
        this.uf = uf;
    }

    public Endereco(DataAddress endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public void updateInfos(DataAddress data) {
        if (data.logradouro() != null){
            this.logradouro = data.logradouro();
        }
        if (data.bairro() != null){
            this.bairro = data.bairro();
        }
        if (data.cep() != null){
            this.cep = data.cep();
        }
        if (data.numero() != null){
            this.numero = data.numero();
        }
        if (data.complemento() != null){
            this.complemento = data.complemento();
        }
        if (data.cidade() != null){
            this.cidade = data.cidade();
        }
        if (data.uf() != null){
            this.uf = data.uf();
        }
    }
}
