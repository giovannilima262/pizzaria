package com.uds.sistema.pizzaria.enums;

public enum AdicionalEnum {

    EXTRA_BACON("Extra bacon"),
    SEM_CEBOLA("Sem cebola"),
    BORDA_RECHEADA("Borda recheada");

    private String descricao;

    AdicionalEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
