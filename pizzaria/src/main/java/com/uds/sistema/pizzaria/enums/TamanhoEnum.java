package com.uds.sistema.pizzaria.enums;

public enum TamanhoEnum {

    PEQUENA("Pequena"),
    MEDIA("Média"),
    GRANDE("Grande");

    private String descricao;

    TamanhoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
