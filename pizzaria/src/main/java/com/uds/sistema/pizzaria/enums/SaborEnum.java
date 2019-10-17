package com.uds.sistema.pizzaria.enums;

public enum SaborEnum {

    CALABRESA("Calabresa"),
    MARGUARITA("Marguerita"),
    PORTUGUESA("Portuguesa");

    private String descricao;

    SaborEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
